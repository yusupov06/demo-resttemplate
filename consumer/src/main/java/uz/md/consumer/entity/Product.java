package uz.md.consumer.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    private String name;
    private String description;
    private Double price;

}
