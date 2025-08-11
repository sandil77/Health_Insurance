package com.cms.claim.service;

import com.cms.claim.dao.ClaimDao;
import com.cms.claim.entity.Claim;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClaimServiceImplTest {

    @InjectMocks
    private ClaimServiceImpl claimService;

    @Mock
    private ClaimDao claimDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddClaim() {
        Claim claim = new Claim();
         claim.setClaimId("1"); //  Use a String
        
        claim.setClaimAmount(BigDecimal.valueOf(1000));

        when(claimDao.save(claim)).thenReturn(claim);

        Claim result = claimService.addClaim(claim);
        assertNotNull(result);
//        assertEquals(1, result.getClaimId());
        assertEquals("1", result.getClaimId());
        assertEquals(BigDecimal.valueOf(1000), result.getClaimAmount());
    }

    @Test
    public void testGetClaimById_Found() {
        Claim claim = new Claim();
        claim.setClaimId("1"); // ✅ Use a String


        when(claimDao.findById(1)).thenReturn(Optional.of(claim));

        Claim result = claimService.getById(1);
        assertNotNull(result);
//        assertEquals(1, result.getClaimId());
        assertEquals("1", result.getClaimId());
    }

    @Test
    public void testGetClaimById_NotFound() {
        when(claimDao.findById(1)).thenReturn(Optional.empty());

        Claim result = claimService.getById(1);
        assertNull(result);
    }

    @Test
    public void testGetAllClaims() {
        List<Claim> claims = Arrays.asList(new Claim(), new Claim());

        when(claimDao.findAll()).thenReturn(claims);

        List<Claim> result = claimService.getAll();
        assertEquals(2, result.size());
    }

}
