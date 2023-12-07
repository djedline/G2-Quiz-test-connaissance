package iut.sae.modele.reseau.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static iut.sae.modele.reseau.DiffieHellman.isPremier;
import static iut.sae.modele.reseau.DiffieHellman.isGenerateur;
import static iut.sae.modele.reseau.DiffieHellman.puissanceModulo;

/**
 * Utilisation de la classe Diffie-Hellman et test de ses méthodes
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
 * @version 1.0
 */
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
		
		assertTrue(isGenerateur(3, 43));
	}
	
	@Test
	void testPuissanceModulo() {
		assertEquals(1, puissanceModulo(20, 0, 30));
		assertEquals(20, puissanceModulo(20, 1, 30));
		assertEquals(10, puissanceModulo(20, 2, 30));
	}
}
