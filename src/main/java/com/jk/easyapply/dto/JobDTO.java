package com.jk.easyapply.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDTO {

    private Long id;
    @NotBlank(message = "Title required")
    private String title;

    @NotBlank(message = "Company required")
    private String company;

    @NotBlank(message = "Apply link required")
    private String applyLink;

    private String location;
    private String batch;
    private String techStack;
    private String shortDescription;
    private String fullDescription;

    private String postedDate;
}
