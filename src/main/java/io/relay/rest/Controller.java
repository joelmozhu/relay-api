package io.relay.rest;

import io.relay.model.api.AggregatedDto;
import io.relay.model.api.CreditNoteDto;
import io.relay.model.api.InvoiceDto;
import io.relay.model.api.PagedResponse;
import io.relay.service.RelayService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${resource.path}")
public class Controller {

    private final RelayService relayService;

    @Autowired
    public Controller(RelayService relayService) {
        this.relayService = relayService;
    }

    @PostMapping(value = "/invoices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<InvoiceDto> createInvoices(@RequestBody List<InvoiceDto> invoices) {
        return relayService.saveInvoices(invoices);
    }

    @PostMapping(value = "/creditnotes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<CreditNoteDto> createCreditNotes(@RequestBody List<CreditNoteDto> creditNotes) {
        return relayService.saveCreditNotes(creditNotes);
    }

    @GetMapping(value = "/getAggregatedView", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedResponse<AggregatedDto> getAggregatedInformation() {
        return new PagedResponse<>(relayService.getAggregatedView());
    }
}