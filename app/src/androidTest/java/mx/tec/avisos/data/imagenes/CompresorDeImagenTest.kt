package mx.tec.avisos.data.imagenes

import android.graphics.BitmapFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import mx.tec.avisos.fotoDePrueba
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * El compresor de imagen sí se prueba, pero con sus propios insumos: una foto
 * gigante de prueba y la revisión de sus dimensiones resultantes.
 */
@RunWith(AndroidJUnit4::class)
class CompresorDeImagenTest {

    private val contexto = InstrumentationRegistry.getInstrumentation().targetContext
    private val compresor = CompresorDeImagen(contexto)

    @Test
    fun una_foto_de_3000px_se_reduce_a_menos_de_1280px() = runBlocking {
        val gigante = fotoDePrueba(contexto, 3000, 2000)

        val reducida = compresor.comprimir(gigante)

        val opciones = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        BitmapFactory.decodeByteArray(reducida, 0, reducida.size, opciones)

        assertTrue(opciones.outWidth <= 1280)
        assertTrue(opciones.outHeight <= 1280)
    }
}
