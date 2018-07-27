package factoring.core.masterdata.client;
import factoring.core.masterdata.client.exception.ClientNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Api(description = "API for CRUD operations on Clients.")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping(value = "/client")
    @ApiOperation(value = "List all the clients.",
            notes = "ce sont des notes")
    public List<Client> listClients(){
        return clientRepository.findAll();
    }

    @GetMapping(value = "/client/{id}")
    @ApiOperation(value = "Get a client according to its id.")
    public Client getClientById(@PathVariable int id){
        Client client = clientRepository.findById(id);
        if (client == null)
            throw new ClientNotFoundException(id);
        return client;
    }

    @PostMapping(value = "/client")
    @ApiOperation(value = "Add a client.")
    public ResponseEntity<Void> addClient(@RequestBody Client client){
        Client clientAdded = clientRepository.save(client);

        if (clientAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientAdded.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/clients")
    @ApiOperation(value = "Add several clients.")
    public ResponseEntity<Void> addClients(@RequestBody List<Client> clients){
        for (Client clientLoop : clients) {
            clientLoop = clientRepository.save(clientLoop);
        }
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping(value = "/client/{id}")
    @ApiOperation(value = "Delete a client according to its id.")
    public ResponseEntity<Void> deleteClientById(@PathVariable int id){
        Client client = clientRepository.findById(id);
        if (client == null) {
            throw new ClientNotFoundException(id);
        }

        clientRepository.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(value = "/clients")
    @ApiOperation(value = "Delete all clients.")
    public ResponseEntity<Void> deleteClients(){
        clientRepository.deleteAll();
        return ResponseEntity.accepted().build();
    }
}
