package de.srh.toolify.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.srh.toolify.dto.ToolifyResponse;
import de.srh.toolify.entities.PurchasesEntity;
import de.srh.toolify.services.PurchaseService;
import de.srh.toolify.validators.PurchasePropsValidator;
import de.srh.toolify.validators.ValidatorUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Validated
@Tag(name = "Purchase", description = "The Purchase APIs to buy products")
@RequestMapping("/private/api/purchase/product")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;

	@PostMapping
	public ResponseEntity<ToolifyResponse> purchase(@Valid @RequestBody final Map<String, Object> purchaseProps) {
		try {
			purchaseProps.forEach((k, v) -> System.out.println(k + ":" + v));
			ValidatorUtil.validate(purchaseProps, PurchasePropsValidator.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ToolifyResponse(String.format(e.getCause().getLocalizedMessage()), 400, HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
		}

		try {
			PurchasesEntity purchase = purchaseService.purchase(purchaseProps);
			return new ResponseEntity<>(new ToolifyResponse(purchase.getTotalPrice().toPlainString(), 200, HttpStatus.OK),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ToolifyResponse(String.format(e.getMessage()), 500, HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
