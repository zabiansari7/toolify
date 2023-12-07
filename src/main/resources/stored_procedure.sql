DELIMITER //
CREATE PROCEDURE productQtyTrigger(IN p_productId INT, IN bought_quantity INT)
BEGIN
    DECLARE exisitng_product_quantity INT;
    
    -- Get the current quantity of the product in the products table
    SELECT quantity INTO exisitng_product_quantity
    FROM products
    WHERE productId = p_productId;

    -- Check if the subtraction would result in a negative quantity
    IF (exisitng_product_quantity - bought_quantity) < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Quantity cannot go below 0';
    ELSE
        -- Update the quantity in the products table
        UPDATE products
        SET quantity = exisitng_product_quantity - bought_quantity
        WHERE productId = p_productId;
    END IF;
END;
//
DELIMITER ;


SHOW CREATE PROCEDURE productQtyTrigger;