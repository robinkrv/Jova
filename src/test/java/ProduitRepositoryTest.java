import com.jova.jova.dao.ProduitService;
import com.jova.jova.dao.impl.ProduitRepository;
import com.jova.jova.entity.Produit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProduitRepositoryTest {

    @Mock
    ProduitRepository produitRepository;

    @InjectMocks
    ProduitService produitService; // Si vous avez un service à tester

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByType() {
        // Création de données factices pour le test
        List<Produit> produits = new ArrayList<>();
        Produit produit1 = new Produit();
        produit1.setType("cafe");
        produits.add(produit1);

        // Configurer le comportement du mock ProduitRepository
        when(produitRepository.findByType("cafe")).thenReturn(produits);

        // Appeler la méthode à tester
        List<Produit> produitsTrouves = produitService.getProduitsByType("cafe");

        // Vérifier les résultats
        assertEquals(produits, produitsTrouves);
    }

    @Test
    void testFindByNomContainingIgnoreCase() {
        // Création de données factices pour le test
        List<Produit> produits = new ArrayList<>();
        Produit produit1 = new Produit();
        produit1.setNom("Café Arabica");
        produits.add(produit1);

        // Configurer le comportement du mock ProduitRepository
        when(produitService.getProduitsByType("cafe")).thenReturn(produits);

        // Appeler la méthode à tester
        List<Produit> produitsTrouves = produitService.getProduitsByType("cafe");

        // Vérifier les résultats
        assertEquals(produits, produitsTrouves);
    }

    // Ajoutez d'autres tests selon les méthodes que vous souhaitez tester
}

