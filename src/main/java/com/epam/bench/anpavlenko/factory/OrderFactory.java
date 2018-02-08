package com.epam.bench.anpavlenko.factory;

import com.epam.bench.anpavlenko.entity.Order;
import com.epam.bench.anpavlenko.entity.PreOrder;

/**
 * @author an.pavlenko
 */
//TODO see implementation comments
public interface OrderFactory {

  Order createOrder(PreOrder preOrder);
}
