package com.shoppingCart.repositories;

import com.shoppingCart.dto.OrderDetailDTO;
import com.shoppingCart.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    List<OrderDetail> findByOrderIdId(Long orderId);
    @Query(
            value = "select od.product_id as productId from order_details od inner join orders o on od .order_id = o.id where o.status ='PENDING' or o.status = 'PENDING_PAYMENT' and od.order_id =:orderId and  od.product_id  IN :productIds",
            nativeQuery = true
    )
    List<OrderDetailDTO> findByOrderIds(@RequestParam Long orderId, @RequestParam List<Long> productIds);



    List<OrderDetail> findByProductIdInAndOrderIdId(List<Long> inventoryIdList,Long orderId);

}
