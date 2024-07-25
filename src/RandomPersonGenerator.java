import java.util.*;

/**
 * This class represents an individual with various attributes.
 */
class Person {
    String name;
    int age;
    String occupation;
    String musicTaste;
    String religiousBelief;
    String culinaryTaste;
    String gender;

    public Person(String name, int age, String occupation, String musicTaste,
                  String religiousBelief, String culinaryTaste, String gender) {
        this.name = name;
        this.age = age;
        this.occupation = occupation;
        this.musicTaste = musicTaste;
        this.religiousBelief = religiousBelief;
        this.culinaryTaste = culinaryTaste;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Age: %d, Occupation: %s, Music Taste: %s, " +
                        "Religious Belief: %s, Culinary Taste: %s, Gender: %s",
                name, age, occupation, musicTaste, religiousBelief,
                culinaryTaste, gender);
    }
}

/**
 * This class represents an affinity group with its members and criteria.
 */
class AffinityGroup {
    String name;
    Map<String, Integer> affinityCriteria;
    List<Person> members;

    public AffinityGroup(String name) {
        this.name = name;
        this.affinityCriteria = new HashMap<>();
        this.members = new ArrayList<>();
    }

    public void addAffinityCriterion(String criterion, int value) {
        affinityCriteria.put(criterion, value);
    }

    public void addMember(Person person) {
        members.add(new Person(person.name, person.age, person.occupation,
                person.musicTaste, person.religiousBelief,
                person.culinaryTaste, person.gender));
    }
}

/**
 * Main class for generating and managing random persons and affinity groups.
 */
public class RandomPersonGenerator {
    private static final Random random = new Random();

    /**
     * Generates a random name.
     */
    public static String generateName() {
        String[] firstNames = {"Ana", "Juan", "María", "Carlos", "Sofía", "Luis", "Elena", "Pedro", "Laura", "Miguel"};
        String[] lastNames = {"García", "Rodríguez", "Martínez", "López", "González", "Pérez", "Sánchez", "Ramírez", "Torres", "Flores"};
        return firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)];
    }

    /**
     * Generates a random age between 18 and 97.
     */
    public static int generateAge() {
        return random.nextInt(80) + 18;
    }

    /**
     * Generates a random occupation.
     */
    public static String generateOccupation() {
        String[] occupations = {
                "Engineer", "Doctor", "Teacher", "Lawyer", "Accountant",
                "Designer", "Chef", "Journalist", "Programmer", "Electrician"
        };
        return occupations[random.nextInt(occupations.length)];
    }

    /**
     * Generates a random music taste.
     */
    public static String generateMusicTaste() {
        String[] musicTastes = {
                "Rock", "Pop", "Jazz", "Classical", "Reggaeton", "Electronic"
        };
        return musicTastes[random.nextInt(musicTastes.length)];
    }

    /**
     * Generates a random religious belief.
     */
    public static String generateReligiousBelief() {
        String[] religiousBeliefs = {
                "Christianity", "Islam", "Buddhism", "Hinduism", "Atheism"
        };
        return religiousBeliefs[random.nextInt(religiousBeliefs.length)];
    }

    /**
     * Generates a random culinary taste.
     */
    public static String generateCulinaryTaste() {
        String[] culinaryTastes = {
                "Italian cuisine", "Mexican cuisine", "Japanese cuisine",
                "Vegetarian cuisine", "Fast food"
        };
        return culinaryTastes[random.nextInt(culinaryTastes.length)];
    }

    /**
     * Generates a random gender.
     */
    public static String generateGender() {
        String[] genders = {
                "Male", "Female", "Non-binary", "Gender fluid", "Agender"
        };
        return genders[random.nextInt(genders.length)];
    }

    /**
     * Generates a list of random persons.
     */
    public static List<Person> generatePersonList(int count) {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            persons.add(new Person(
                    generateName(),
                    generateAge(),
                    generateOccupation(),
                    generateMusicTaste(),
                    generateReligiousBelief(),
                    generateCulinaryTaste(),
                    generateGender()
            ));
        }
        return persons;
    }

    /**
     * Creates affinity groups based on the given list of persons.
     */
    public static List<AffinityGroup> createAffinityGroups(List<Person> persons) {
        List<AffinityGroup> groups = new ArrayList<>();

        AffinityGroup musicLovers = new AffinityGroup("Music Lovers");
        musicLovers.addAffinityCriterion("musicTaste", 3);
        musicLovers.addAffinityCriterion("age", 2);
        musicLovers.addAffinityCriterion("gender", 1);
        groups.add(musicLovers);

        AffinityGroup gourmets = new AffinityGroup("Gourmets");
        gourmets.addAffinityCriterion("culinaryTaste", 3);
        gourmets.addAffinityCriterion("occupation", 2);
        gourmets.addAffinityCriterion("religiousBelief", 1);
        groups.add(gourmets);

        AffinityGroup professionals = new AffinityGroup("Professional Network");
        professionals.addAffinityCriterion("occupation", 3);
        professionals.addAffinityCriterion("age", 2);
        professionals.addAffinityCriterion("musicTaste", 1);
        groups.add(professionals);

        for (Person person : persons) {
            for (AffinityGroup group : groups) {
                if (hasAffinityWithGroup(person, group)) {
                    group.addMember(person);
                }
            }
        }

        return groups;
    }

    /**
     * Checks if a person has affinity with a group based on the group's criteria.
     */
    private static boolean hasAffinityWithGroup(Person person, AffinityGroup group) {
        if (group.members.isEmpty()) {
            return true; // First member is always added to the group
        }

        int affinityScore = 0;
        Person representative = group.members.get(0);

        for (Map.Entry<String, Integer> criterion : group.affinityCriteria.entrySet()) {
            switch (criterion.getKey()) {
                case "age":
                    if (Math.abs(person.age - representative.age) <= 5) {
                        affinityScore += criterion.getValue();
                    }
                    break;
                case "occupation":
                    if (person.occupation.equals(representative.occupation)) {
                        affinityScore += criterion.getValue();
                    }
                    break;
                case "musicTaste":
                    if (person.musicTaste.equals(representative.musicTaste)) {
                        affinityScore += criterion.getValue();
                    }
                    break;
                case "religiousBelief":
                    if (person.religiousBelief.equals(representative.religiousBelief)) {
                        affinityScore += criterion.getValue();
                    }
                    break;
                case "culinaryTaste":
                    if (person.culinaryTaste.equals(representative.culinaryTaste)) {
                        affinityScore += criterion.getValue();
                    }
                    break;
                case "gender":
                    if (person.gender.equals(representative.gender)) {
                        affinityScore += criterion.getValue();
                    }
                    break;
            }
        }

        return affinityScore >= 3;
    }

    /**
     * Prints information about the affinity groups.
     */
    public static void printAffinityGroupsInfo(List<AffinityGroup> groups) {
        for (AffinityGroup group : groups) {
            System.out.println("Group: " + group.name);
            int count = 1;
            for (Map.Entry<String, Integer> criterion : group.affinityCriteria.entrySet()) {
                System.out.println("Relation " + count + ": " + criterion.getKey() + " (Value: " + criterion.getValue() + ")");
                count++;
            }
            System.out.println("List of Individuals:");
            for (Person person : group.members) {
                System.out.println("• " + person.name);
            }
            System.out.println();
        }
    }

    /**
     * Prints the list of individuals who don't belong to any affinity group.
     */
    public static void printUnaffiliatedIndividuals(List<Person> allPersons, List<AffinityGroup> groups) {
        Set<String> affiliatedNames = new HashSet<>();
        for (AffinityGroup group : groups) {
            for (Person person : group.members) {
                affiliatedNames.add(person.name);
            }
        }

        System.out.println("Individuals not belonging to any affinity group:");
        for (Person person : allPersons) {
            if (!affiliatedNames.contains(person.name)) {
                System.out.println("• " + person.name);
            }
        }
    }

    public static void main(String[] args) {
        List<Person> allPersons = generatePersonList(1000);
        List<AffinityGroup> affinityGroups = createAffinityGroups(allPersons);

        printAffinityGroupsInfo(affinityGroups);
        printUnaffiliatedIndividuals(allPersons, affinityGroups);
    }
}