package iut.sae.modele;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ImportExportTest {

	@Test
	void test() {
		try {
            ImportExport.importer("src/iut/sae/modele/tests/questionsbasiques");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
