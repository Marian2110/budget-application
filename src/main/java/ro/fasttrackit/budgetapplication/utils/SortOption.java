package ro.fasttrackit.budgetapplication.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class SortOption {
    private String direction;
    private String property;

}
