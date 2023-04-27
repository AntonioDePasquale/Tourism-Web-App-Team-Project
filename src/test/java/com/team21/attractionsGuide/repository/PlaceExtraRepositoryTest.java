package com.team21.attractionsGuide.repository;

import com.team21.attractionsGuide.entity.PlaceExtra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The PlaceExtraRepositoryTest is to test the Repository layer of this application
 *
 * @author Jiayu Ran
 * Date:  26/04/2023
 */

@DataJpaTest
public class PlaceExtraRepositoryTest {

    @Autowired
    private PlaceExtraRepository placeExtraRepository;

    @Autowired
    private TestEntityManager entityManager;

    /**
     * setup
     */
    @BeforeEach
    public void setUp() {
        PlaceExtra pe = new PlaceExtra("place_id1", true, 2.5);
        entityManager.persist(pe);
    }

    /**
     * To test find a place extra information by place_id/location_id
     */
    @Test
    public void testFindByLocationId() {
        PlaceExtra p = placeExtraRepository.findBylocationId("place_id1");
        assertEquals(p.getLocationId(), "place_id1");
    }

}
