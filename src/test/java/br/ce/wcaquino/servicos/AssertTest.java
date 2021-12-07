package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class AssertTest {

    @Test
    public void test(){
        Assert.assertTrue(true);
        Assert.assertFalse(false);

        Assert.assertEquals("Erro de comparacao",1, 1);
        Assert.assertEquals(0.51, 0.51, 0.01); // delta margin error
        Assert.assertEquals(Math.PI, 3.14, 0.01);

        int i = 5;
        Integer i2 = 5;

        Assert.assertEquals(i, i2.intValue());
        //OR
        Assert.assertEquals(Integer.valueOf(i), i2);

        Assert.assertEquals("bola", "bola");
        Assert.assertNotEquals("bola", "casa");
        Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
        Assert.assertTrue("bola".startsWith("bo"));

        Usuario u1 = new Usuario("user1");
        Usuario u2 = new Usuario("user1");
        Usuario u3 = u2;
        Usuario u4 = null;


        Assert.assertEquals(u1.getNome(), u2.getNome());
        Assert.assertSame(u2, u3);

        Assert.assertNull(u4);
    }
}
