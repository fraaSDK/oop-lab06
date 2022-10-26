/**
 * 
 */
package it.unibo.collections.social.impl;

import it.unibo.collections.social.api.SocialNetworkUser;
import it.unibo.collections.social.api.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 
 * This will be an implementation of
 * {@link SocialNetworkUser}:
 * 1) complete the definition of the methods by following the suggestions
 * included in the comments below.
 * 
 * @param <U>
 *            Specific {@link User} type
 */
public final class SocialNetworkUserImpl<U extends User> extends UserImpl implements SocialNetworkUser<U> {

    /*
     *
     * [FIELDS]
     *
     * Define any necessary field
     *
     * In order to save the people followed by a user organized in groups, adopt
     * a generic-type Map:  think of what type of keys and values would best suit the requirements
     */
    private final Map<String, Set<U>> followed = new HashMap<>();

    /*
     * [CONSTRUCTORS]
     *
     * 1) Complete the definition of the constructor below, for building a user
     * participating in a social network, with 4 parameters, initializing:
     *
     * - firstName
     * - lastName
     * - username
     * - age and every other necessary field
     */
    /**
     * Builds a user participating in a social network.
     *
     * @param name
     *            the user firstname
     * @param surname
     *            the user lastname
     * @param userAge
     *            user's age
     * @param user
     *            alias of the user, i.e. the way a user is identified on an
     *            application
     */
    public SocialNetworkUserImpl(final String name, final String surname, final String username, final int userAge) {
        super(name, surname, username, userAge);
    }

    /*
     * 2) Define a further constructor where the age defaults to -1
     */
    public SocialNetworkUserImpl(final String name, final String surname, final String username) {
        super(name, surname, username, -1);
    }

    /*
     * [METHODS]
     *
     * Implements the methods below
     */
    @Override
    public boolean addFollowedUser(final String circle, final U user) {
        //Retrieves the Set<U> of friends in the specified circle (key).
        Set<U> followedSet = this.followed.get(circle);

        //Creates a new set if the provided one was null.
        if (Objects.isNull(followedSet)) {
            followedSet = new HashSet<>();
            this.followed.put(circle, followedSet);
        }

        return followedSet.add(user);
    }

    /**
     *
     * [NOTE] If no group with groupName exists yet, this implementation must
     * return an empty Collection.
     */
    @Override
    public Collection<U> getFollowedUsersInGroup(final String groupName) {
        List<U> groupFollowedUsers = new ArrayList<>();
        if (followed.containsKey(groupName)) {
            groupFollowedUsers.addAll(followed.get(groupName));
        }
        return Objects.nonNull(groupFollowedUsers) ? groupFollowedUsers : Collections.emptyList();
    }

    @Override
    public List<U> getFollowedUsers() {
        //Using a Set will prevent duplicates.
        final Set<U> allFollowedUsers = new HashSet<>();
        
        //A Collection containing all Sets from all friend groups.
        final Collection<Set<U>> allUsers = followed.values();
        
        for (final Set<U> set : allUsers) {
            allFollowedUsers.addAll(set);
        }
        return new ArrayList<>(allFollowedUsers);
    }
}
