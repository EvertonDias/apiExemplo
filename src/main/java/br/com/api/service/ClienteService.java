package br.com.api.service;

import br.com.api.model.Cliente;
import br.com.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes/")
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(ClienteService.class);

    @CrossOrigin(origins = "*")
    @GetMapping("findAll")
    public List<Cliente> findAll() {

        List<Cliente> allClientesList = (List<Cliente>) clienteRepository.findAll();

        return getAllClientesAtivos(allClientesList);
    }

    private List<Cliente> getAllClientesAtivos(List<Cliente> allClientes) {

        List<Cliente> clientesAtivosList = allClientes.stream().filter(cliente -> cliente.getIsAtivo()).collect(Collectors.toList());

        Collections.sort(clientesAtivosList, (a, b) -> a.getNome().compareToIgnoreCase(b.getNome()));

        return  clientesAtivosList;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("delete")
    public void delete(@RequestParam Long id) {
        clienteRepository.desativar(id, false);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("update")
    public void update(@RequestBody Cliente cliente) {

        clienteRepository.save(cliente);
    }

}
