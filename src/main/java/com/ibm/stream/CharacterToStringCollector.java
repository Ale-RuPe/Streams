package com.ibm.stream;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Collects Characters from a string to a String
 */
public class CharacterToStringCollector implements Collector<Character, StringBuilder, String> { 
	
	// T,A,R
	// T--> Character
	// A--> StringBuilder
	// R--> String
	
    @Override
    public Supplier<StringBuilder> supplier() {
        // Devuelve una función que crea el acumulador A para almacenar los elementos T del Stream.
    	// () -> new StringBuilder()
    	return StringBuilder::new;
    }

    @Override
    public BiConsumer<StringBuilder, Character> accumulator() {
    	// Devuelve una función que coge el acumulador A y un elemento T y lo añade en el acumulador A.
    	return (acumSB, caracter) -> {
    		acumSB.append(caracter);
    	};
    }

    @Override
    public BinaryOperator<StringBuilder> combiner() {
    	// Devuelve una función que combina dos acumuladores A en uno solo. 
    	// Se utiliza cuando el Stream es ejecutado en paralelo.
    	return (a,b) -> a.append(b);   
    }

    @Override
    public Function<StringBuilder, String> finisher() {
    	// Devuelve una función que transforma el acumulador A en el resultado R esperado.
        return StringBuilder::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
    	// Informa de las características que nuestra implementación Collector va a tener. 
    	// Estas pueden ser cualquiera de las definidas en java.util.stream.Collector.Characteristics
    	// CONCURRENT, IDENTITY_FINISH, UNORDERED.
        return EnumSet.of(Characteristics.UNORDERED);
    }
}
