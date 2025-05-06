package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;


    @Test
    void deleteBeer() {
        BeerDTO newDTO = BeerDTO.builder()
                .price(new BigDecimal("10.99"))
                .beerName("Mango Monny")
                .beerStyle(BeerStyle.GOSE)
                .quantityOnHand(500)
                .upc("123245")
                .build();

        BeerDTO saved = this.beerClient.createBeer(newDTO);
        System.out.println(saved);
        assertThrows(HttpClientErrorException.class, () -> this.beerClient.deleteBeer(newDTO.getId()));
    }

    @Test
    void updateBeer() {

        BeerDTO savedBeer = beerClient.listBeers().getContent().get(0);

        final String newName = "Mango Fede 2";
        System.out.println(savedBeer);

        savedBeer.setBeerName(newName);
        BeerDTO updatedBeer = beerClient.updateBeer(savedBeer);

        System.out.println(updatedBeer);

        assertEquals(newName, updatedBeer.getBeerName());
    }

    @Test
    void getBeerById() {

        BeerDTO savedBeer = this.beerClient.listBeers().getContent().get(0);
        BeerDTO beer = beerClient.getBeerById(savedBeer.getId());

        assertNotNull(beer);
    }

    @Test
    void listBeerNoName() {

        beerClient.listBeers(null,
                null,
                null,
                null,
                null
                );
    }

    @Test
    void listBeerWBeerName() {

        beerClient.listBeers("ALE",
                null,
                null,
                null,
                null
                );
    }

    @Test
    void listBeerWBeerStyle() {

        beerClient.listBeers(null,
//                EnumUtils.findEnumInsensitiveCase(BeerStyle.class, "PALE_ALE"),
                BeerStyle.PALE_ALE,
                null,
                null,
                null
        );
    }

    @Test
    void listBeerWShowInvTrue() {

        beerClient.listBeers(null,
                null,
                true,
                null,
                null
        );
    }

    @Test
    void listBeerWShowInvFalse() {

        beerClient.listBeers(null,
                null,
                false,
                null,
                null
        );
    }

    @Test
    void listBeerWPageNumb() {

        beerClient.listBeers(null,
                null,
                null,
                3,
                null
        );
    }

    @Test
    void listBeerWPageSize() {

        beerClient.listBeers(null,
                null,
                null,
                null,
                35
        );
    }

    @Test
    void createNewBeer() {
        BeerDTO newDTO = BeerDTO.builder()
                .price(new BigDecimal("10.99"))
                .beerName("Mango Monny")
                .beerStyle(BeerStyle.GOSE)
                .quantityOnHand(500)
                .upc("123245")
                .build();

        BeerDTO saved = this.beerClient.createBeer(newDTO);
        System.out.println(saved);
        assertNotNull(saved);
    }

}