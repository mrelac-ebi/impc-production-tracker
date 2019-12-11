/******************************************************************************
 Copyright 2019 EMBL - European Bioinformatics Institute

 Licensed under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License. You may obtain a copy of the License at
 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 either express or implied. See the License for the specific
 language governing permissions and limitations under the
 License.
 */
package org.gentar.biology.project;

import org.gentar.biology.project.search.ProjectSearcherService;
import org.gentar.biology.project.search.Search;
import org.gentar.biology.project.search.SearchReport;
import org.gentar.biology.project.search.SearchReportDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.gentar.biology.project.search.filter.ProjectFilter;
import org.gentar.biology.project.search.filter.ProjectFilterBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/projects/search")
@CrossOrigin(origins = "*")
public class ProjectSearcherController
{
    private ProjectSearcherService projectSearcherService;
    private SearchReportMapper searchReportMapper;

    public ProjectSearcherController(
        ProjectSearcherService projectSearcherService,
        SearchReportMapper searchReportMapper)
    {
        this.projectSearcherService = projectSearcherService;
        this.searchReportMapper = searchReportMapper;
    }

    @GetMapping
    public ResponseEntity search(
        Pageable pageable,
        @RequestParam(value = "searchTypeName", required = false) String searchTypeName,
        @RequestParam(value = "input", required = false) List<String> inputs,
        @RequestParam(value = "tpn", required = false) List<String> tpns,
        @RequestParam(value = "privacyName", required = false) List<String> privacies,
        @RequestParam(value = "workUnitName", required = false) List<String> workUnitsNames,
        @RequestParam(value = "workGroupName", required = false) List<String> workGroupNames)
    {
        ProjectFilter projectFilter = ProjectFilterBuilder.getInstance()
            .withTpns(tpns)
            .withWorkUnitNames(workUnitsNames)
            .withWorkGroupNames(workGroupNames)
            .withPrivacies(privacies)
            .build();
        Search search = new Search(searchTypeName, inputs, projectFilter);
        SearchReport searchReport = projectSearcherService.executeSearch(search, pageable);
        SearchReportDTO searchReportDTO = searchReportMapper.toDto(searchReport);

        return new ResponseEntity<>(searchReportDTO, HttpStatus.OK);
    }
}
