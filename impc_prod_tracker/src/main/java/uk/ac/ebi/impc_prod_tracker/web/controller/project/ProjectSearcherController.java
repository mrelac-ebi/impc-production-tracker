package uk.ac.ebi.impc_prod_tracker.web.controller.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.ebi.impc_prod_tracker.common.pagination.PaginationHelper;
import uk.ac.ebi.impc_prod_tracker.conf.error_management.OperationFailedException;
import uk.ac.ebi.impc_prod_tracker.service.project.search.ProjectSearcherService;
import uk.ac.ebi.impc_prod_tracker.service.project.search.Search;
import uk.ac.ebi.impc_prod_tracker.service.project.search.SearchBuilder;
import uk.ac.ebi.impc_prod_tracker.service.project.search.SearchReport;
import uk.ac.ebi.impc_prod_tracker.service.project.search.SearchResult;
import uk.ac.ebi.impc_prod_tracker.web.dto.project.search.SearchReportDTO;
import uk.ac.ebi.impc_prod_tracker.web.mapping.project.SearchReportMapper;

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
        @RequestParam(value = "searchType", required = false) String searchType,
        @RequestParam(value = "input", required = false) List<String> inputs,
        @RequestParam(value = "tpn", required = false) List<String> tpns)
    {
        try
        {
            Search search = SearchBuilder.getInstance()
                .withSearchType(searchType)
                .withInputs(inputs)
                .build();
            SearchReport searchReport = projectSearcherService.executeSearch(search);

            Page<SearchResult> paginatedContent =
                PaginationHelper.createPage(searchReport.getResults(), pageable);
            searchReport.setResults(paginatedContent.getContent());
            PagedModel.PageMetadata pageMetadata = buildPageMetadata(paginatedContent);
            SearchReportDTO searchReportDTO = searchReportMapper.toDto(searchReport);
            searchReportDTO.setPageMetadata(pageMetadata);

            return new ResponseEntity<>(searchReportDTO, HttpStatus.OK);

        }
        catch (Exception e)
        {
            throw new OperationFailedException(e.getMessage());
        }
    }

    private PagedModel.PageMetadata buildPageMetadata(Page<SearchResult> paginatedContent)
    {
        return new PagedModel.PageMetadata(
            paginatedContent.getSize(),
            paginatedContent.getNumber(),
            paginatedContent.getTotalElements(),
            paginatedContent.getTotalPages());
    }
}