package backendminhagab.example.MinhaGab.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backendminhagab.example.MinhaGab.models.GabRequest;
import backendminhagab.example.MinhaGab.services.GabRequestService;

@RestController
@RequestMapping("/gab_requests")
public class GabRequestController {

     private final GabRequestService gabRequestService;

    public GabRequestController(GabRequestService gabsRequestService) {
        this.gabRequestService = gabsRequestService;
    }

    @PostMapping("/create")
    public ResponseEntity<GabRequest> createGabsRequest(@RequestBody GabRequest gabRequest) {
        GabRequest newRequest = gabRequestService.createGabsRequest(gabRequest);
        return ResponseEntity.ok(newRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGabRequest(@PathVariable Integer id) {
        gabRequestService.deletarGabRequest(id);
        return ResponseEntity.noContent().build(); // HTTP 204 
    }

    @GetMapping
    public ResponseEntity<List<GabRequest>> getAllRequests() {
        List<GabRequest> requests = gabRequestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

}
