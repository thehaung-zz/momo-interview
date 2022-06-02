package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Hau Nguyen
 * @created : 6/1/22
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cash {
    private Long id;

    private Double price;

    private Integer quantity;
}
