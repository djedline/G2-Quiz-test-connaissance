package iut.sae.modele;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ImportExportTest {

	@Test
	void test() {
		ImportExport.importer("src/iut/sae/modele/tests/questionsbasiques");
	}

}
