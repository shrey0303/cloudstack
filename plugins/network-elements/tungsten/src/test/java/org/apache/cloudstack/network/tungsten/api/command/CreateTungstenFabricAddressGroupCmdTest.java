// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package org.apache.cloudstack.network.tungsten.api.command;

import com.cloud.exception.ConcurrentOperationException;
import com.cloud.exception.InsufficientCapacityException;
import com.cloud.exception.NetworkRuleConflictException;
import com.cloud.exception.ResourceAllocationException;
import com.cloud.exception.ResourceUnavailableException;
import org.apache.cloudstack.api.ServerApiException;
import org.apache.cloudstack.network.tungsten.api.response.TungstenFabricAddressGroupResponse;
import org.apache.cloudstack.network.tungsten.service.TungstenService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

public class CreateTungstenFabricAddressGroupCmdTest {

    @Mock
    TungstenService tungstenService;

    CreateTungstenFabricAddressGroupCmd createTungstenFabricAddressGroupCmd;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        createTungstenFabricAddressGroupCmd = new CreateTungstenFabricAddressGroupCmd();
        createTungstenFabricAddressGroupCmd.tungstenService = tungstenService;
        Whitebox.setInternalState(createTungstenFabricAddressGroupCmd, "zoneId", 1L);
        Whitebox.setInternalState(createTungstenFabricAddressGroupCmd, "name", "test");
        Whitebox.setInternalState(createTungstenFabricAddressGroupCmd, "ipPrefix", "test");
        Whitebox.setInternalState(createTungstenFabricAddressGroupCmd, "ipPrefixLen", 1);
    }

    @Test
    public void executeTest() throws ResourceUnavailableException, InsufficientCapacityException, ServerApiException,
            ConcurrentOperationException, ResourceAllocationException, NetworkRuleConflictException {
        TungstenFabricAddressGroupResponse tungstenFabricAddressGroupResponse =
                Mockito.mock(TungstenFabricAddressGroupResponse.class);
        Mockito.when(tungstenService.createTungstenAddressGroup(ArgumentMatchers.anyLong(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyInt())).thenReturn(tungstenFabricAddressGroupResponse);
        createTungstenFabricAddressGroupCmd.execute();
        Assert.assertEquals(tungstenFabricAddressGroupResponse,
                createTungstenFabricAddressGroupCmd.getResponseObject());
    }
}
