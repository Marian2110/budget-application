package ro.fasttrackit.budgetapplication.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class Criteria {
    @Min(value = 1, message = "Page number must be greater than 0")
    private int page;
    @Min(value = 1, message = "Page size must be greater than 0")
    private int size;
    @JsonProperty("sortOptions")
    private List<SortOption> sortOptions;
    private List<FilterOption> filterOptions;

}
