package iut.sae.modele.reseau.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static iut.sae.modele.reseau.DiffieHellman.isPremier;
import static iut.sae.modele.reseau.DiffieHellman.isGenerateur;

class TestDiffieHellman {

	@Test
	void testIsPremier() {
		assertFalse(isPremier(1));
		assertFalse(isPremier(4));
		
		assertTrue(isPremier(71));
		assertTrue(isPremier(7));
		assertTrue(isPremier(101));
	}
	
	@Test
	void testIsGenerateur() {
		assertFalse(isGenerateur(0, 5));
		assertFalse(isGenerateur(1, 5));
		assertTrue(isGenerateur(2, 5));
		assertTrue(isGenerateur(3, 5));
		assertFalse(isGenerateur(4, 5));
		
		assertFalse(isGenerateur(0, 7));
		assertFalse(isGenerateur(1, 7));
		assertFalse(isGenerateur(2, 7));
		assertTrue(isGenerateur(3, 7));
		assertFalse(isGenerateur(4, 7));
		assertTrue(isGenerateur(5, 7));
		assertFalse(isGenerateur(6, 7));
		
	}

}
