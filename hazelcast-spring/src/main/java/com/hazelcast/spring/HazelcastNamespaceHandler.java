/*
 * Copyright (c) 2008-2018, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.spring;

import com.hazelcast.spring.hibernate.RegionFactoryBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Hazelcast Custom Namespace Definitions.
 */
public class HazelcastNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("config", new HazelcastConfigBeanDefinitionParser());
        registerBeanDefinitionParser("hazelcast", new HazelcastInstanceDefinitionParser());
        registerBeanDefinitionParser("client", new HazelcastClientBeanDefinitionParser());
        registerBeanDefinitionParser("hibernate-region-factory", new RegionFactoryBeanDefinitionParser());
        registerBeanDefinitionParser("cache-manager", new CacheManagerBeanDefinitionParser());
        String[] types = {
                "map",
                "multiMap",
                "replicatedMap",
                "queue",
                "topic",
                "set",
                "list",
                "executorService",
                "durableExecutorService",
                "scheduledExecutorService",
                "ringbuffer",
                "cardinalityEstimator",
                "idGenerator",
                "flakeIdGenerator",
                "atomicLong",
                "atomicReference",
                "countDownLatch",
                "semaphore",
                "lock",
                "reliableTopic",
                "PNCounter",
                };
        for (String type : types) {
            registerBeanDefinitionParser(type, new HazelcastTypeBeanDefinitionParser(type));
        }
    }
}
