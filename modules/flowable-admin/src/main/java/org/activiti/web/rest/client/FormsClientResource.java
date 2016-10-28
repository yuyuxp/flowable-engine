/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.web.rest.client;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.domain.EndpointType;
import org.activiti.domain.ServerConfig;
import org.activiti.service.engine.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Bassam Al-Sarori
 */
@RestController
public class FormsClientResource extends AbstractClientResource {

    @Autowired
    protected FormService clientService;

    /**
     * GET a list of deployed forms.
     */
    @RequestMapping(value = "/rest/activiti/forms", method = RequestMethod.GET, produces = "application/json")
    public JsonNode listForms(HttpServletRequest request) {
        ServerConfig serverConfig = retrieveServerConfig(EndpointType.FORM);
        Map<String, String[]> parameterMap = getRequestParametersWithoutServerId(request);
        return clientService.listForms(serverConfig, parameterMap);
    }
    
    /**
     * GET process definition's list of deployed forms.
     */
    @RequestMapping(value = "/rest/activiti/process-definition-forms/{processDefinitionId}", method = RequestMethod.GET, produces = "application/json")
    public JsonNode getProcessDefinitionForms(@PathVariable String processDefinitionId, HttpServletRequest request) {
        return clientService.getProcessDefinitionForms(retrieveServerConfig(EndpointType.FORM), processDefinitionId);
    }
}