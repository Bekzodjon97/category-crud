package bek.dev.categorycrudapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
    private Integer parentCategoryId;
}
