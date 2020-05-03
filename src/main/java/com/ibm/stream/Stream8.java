package com.ibm.stream;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream8 {

    public static List<Integer> returnSquareRoot(List<Integer> numbers){
         return numbers.stream()
        		 .map(i -> (int)Math.sqrt(i))
        		 .collect(Collectors.toList());
    }

    public static List<Integer> getAgeFromUsers(List<User> user){
        return user.stream().map(User::getAge).collect(Collectors.toList());
    }

    public static List<Integer> getDistinctAges(List<User> users){
        return users.stream().map(User::getAge).distinct().collect(Collectors.toList());
    }

    public static List<User> getLimitedUserList(List<User> users, int limit){
    	return users.stream().limit(limit).collect(Collectors.toList());
    }

    public static Integer countUsersOlderThen25(List<User> users){
        return (int)users.stream().filter(u -> u.getAge()>25).count();
    }

    public static List<String> mapToUpperCase(List<String> strings){
    	return strings.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    public static Integer sum(List<Integer> integers){
    	return integers.stream()
    		.mapToInt(Integer::intValue)
    		.sum();
        //return integers.stream().reduce(0, (x,y) -> x + y); 
    }

    public static List<Integer> skip(List<Integer> integers, Integer toSkip){
        return integers.stream().skip(toSkip).collect(Collectors.toList());
    }

    public static List<String> getFirstNames(List<String> names){
    	//Duda
        return names
        		.stream()
        		.map(n -> n.split(" ")[0])
        		.collect(Collectors.toList());
    }

    public static List<String> getDistinctLetters(List<String> names){
    	return names.stream()
    			.map(n -> n.split(""))
    			.flatMap(Arrays::stream)
    			.distinct()
    			.collect(Collectors.toList());
    }


    public static String separateNamesByComma(List<User> users){
        return users.stream()
        		.map(User::getName)
        		.reduce("", (x,y) -> x+", "+y)
        		.substring(2);
    }

    public static double getAverageAge(List<User> users){
        return users.stream()
        		.mapToInt(User::getAge)
        		.average().getAsDouble();
    }

    public static Integer getMaxAge(List<User> users){
        return users.stream()
        		.map(User::getAge)
        		.max(Integer::compareTo).get();
    }

    public static Integer getMinAge(List<User> users) {
        return users.stream()
        		.map(User::getAge)
        		.min(Integer::compareTo).get();
    }

    public static Map<Boolean, List<User>> partionUsersByGender(List<User> users){
    	return users.stream()
    			.collect( 
    				Collectors.groupingBy(User::isMale, Collectors.toCollection(ArrayList::new)) 
				);
    }

    public static Map<Integer, List<User>> groupByAge(List<User> users){
        return users
        		.stream()
    			.collect( 
					Collectors.groupingBy(User::getAge, Collectors.toCollection(ArrayList::new)) 
				);
    }

    public static Map<Boolean, Map<Integer, List<User>>> groupByGenderAndAge(List<User> users){
    	return users.stream()
    			.collect( 
        				Collectors.groupingBy(
    						User::isMale, Collectors.groupingBy(
    								User::getAge, Collectors.toCollection(ArrayList::new)
    						) 
        				) 
    				);
    }

    public static Map<Boolean, Long> countGender(List<User> users){
    	return users.stream()
    			.collect( 
    			Collectors.groupingBy(User::isMale, Collectors.counting()) );
    }

    public static boolean anyMatch(List<User> users, int age){
        return users.stream()
        		.map(User::getAge)
        		.anyMatch(u -> u.equals(age));
    }

    public static boolean noneMatch(List<User> users, int age){
    	return users.stream().map(User::getAge).noneMatch(u -> u.equals(age));
    }

    public static Optional<User> findAny(List<User> users, String name){
        return users.stream()
        		.filter(u -> u.getName().equals(name))
        		.findAny();
    }

    public static List<User> sortByAge(List<User> users){
//    	return users.stream()
//    			.sorted((u1,u2) -> u1.getAge().compareTo(u2.getAge()) )
//    			.collect(Collectors.toList());
        return users.stream()
        		.sorted(Comparator.comparing(User::getAge))
        		.collect(Collectors.toList());
    }

    public static Stream<Integer> getBoxedStream(IntStream stream){
        return stream.boxed();
    }

    public static List<Integer> generateFirst10PrimeNumbers(){
        return Stream
        		.iterate(1, i -> i+1)
        		.filter(i -> i != 1)
                .filter(Stream8::isPrime)
                .limit(10)
                .collect(Collectors.toList());
    }

    public static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, number/2).noneMatch(i -> number%i == 0);
    }

    public static List<Integer> generate10RandomNumbers(){
    	//IntStream.rangeClosed(1, 10).map(d->d);
        return Stream.iterate(1, i -> i+1)
        		.map(i -> Math.random())
        		.map(Double::intValue)
        		.limit(10).collect(Collectors.toList());
    }

    public static User findOldest(List<User> users){
        return users.stream()
        		.max(Comparator.comparing(User::getAge))
        		.get();
    }

    public static int sumAge(List<User> users){
        return users.stream()
        		.mapToInt(User::getAge)
        		.sum();
    }

    public static IntSummaryStatistics ageSummaryStatistics(List<User> users){
        return users.stream().mapToInt(User::getAge).summaryStatistics();
    }

}
