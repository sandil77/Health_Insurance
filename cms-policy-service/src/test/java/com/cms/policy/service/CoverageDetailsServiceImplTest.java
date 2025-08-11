package com.cms.policy.service;

import com.cms.policy.dao.CoverageDetailsDao;
import com.cms.policy.entity.CoverageDetails;
import com.cms.policy.entity.Policy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CoverageDetailsServiceImplTest {

    @InjectMocks
    private CoverageDetailsServiceImpl service;  // ✅ This is what "service" refers to

    @Mock
    private CoverageDetailsDao coverageDetailsDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);  // ✅ Initializes the @Mock and @InjectMocks
    }
    @Test
    void testGetCoverageByPolicyId() {
        // Mock Policy
        Policy policy = new Policy();
        policy.setId(1);
        policy.setName("Basic Health Plan");

        // Mock CoverageDetails
        CoverageDetails details = new CoverageDetails();
        details.setType("Health");
        details.setDescription("Covers hospitalization");
        details.setAmount(BigDecimal.valueOf(5000));
        details.setPolicy(policy);

        List<CoverageDetails> mockList = Collections.singletonList(details);

        // Mock DAO behavior
        when(coverageDetailsDao.findByPolicy(any(Policy.class))).thenReturn(mockList);

        // Call service method
        List<CoverageDetails> result = service.getByPolicy(policy);


        // Assertions
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Health", result.get(0).getType());
        assertEquals("Covers hospitalization", result.get(0).getDescription());
        assertEquals(BigDecimal.valueOf(5000), result.get(0).getAmount());
        assertEquals(policy, result.get(0).getPolicy());

        // Verify repository call
        verify(coverageDetailsDao, times(1)).findByPolicy(any(Policy.class));
    }
    @Test
    public void testGetById_WhenNotFound() {
        when(coverageDetailsDao.findById(99)).thenReturn(Optional.empty());

        CoverageDetails found = service.getById(99);

        assertNull(found);
    }

    
    @Test
    public void testGetByPolicy_EmptyResults() {
        Policy policy = new Policy();
        policy.setId(2);

        when(coverageDetailsDao.findByPolicy(policy)).thenReturn(Collections.emptyList());

        List<CoverageDetails> result = service.getByPolicy(policy);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    public void testGetByPolicy_WithResults() {
        Policy policy = new Policy();
        policy.setId(1);

        CoverageDetails detail1 = new CoverageDetails();
        detail1.setType("Health");

        CoverageDetails detail2 = new CoverageDetails();
        detail2.setType("Vision");

        List<CoverageDetails> mockList = Arrays.asList(detail1, detail2);

        when(coverageDetailsDao.findByPolicy(policy)).thenReturn(mockList);

        List<CoverageDetails> result = service.getByPolicy(policy);

        assertNotNull(result);
        assertEquals(2, result.size());
    }
    @Test
    public void testAddCoverageDetail() {
        CoverageDetails detail = new CoverageDetails();
        detail.setType("Health");
        detail.setDescription("Covers hospitalization");
        detail.setAmount(BigDecimal.valueOf(5000));

        when(coverageDetailsDao.save(detail)).thenReturn(detail);

        CoverageDetails saved = service.addCoverageDetail(detail);

        assertNotNull(saved);
        assertEquals("Health", saved.getType());
        verify(coverageDetailsDao, times(1)).save(detail);
    }
}
