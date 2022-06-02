package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author : Hau Nguyen
 * @created : 6/1/22
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;

    private List<Product> products;

    private Date orderDate = new Date();
}
