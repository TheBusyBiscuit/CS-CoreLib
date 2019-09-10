package me.mrCookieSlime.CSCoreLibPlugin.general;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

@SuppressWarnings("unused")
public class DataAPI {

    private DataAPI() {}

    /////////////////////////////////////
    // Setters
    /////////////////////////////////////
    public static void setByte(PersistentDataHolder holder, NamespacedKey key, byte value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.BYTE, value);
    }

    public static void setShort(PersistentDataHolder holder, NamespacedKey key, short value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.SHORT, value);
    }

    public static void setInt(PersistentDataHolder holder, NamespacedKey key, int value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, value);
    }

    public static void setLong(PersistentDataHolder holder, NamespacedKey key, long value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.LONG, value);
    }

    public static void setFloat(PersistentDataHolder holder, NamespacedKey key, float value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.FLOAT, value);
    }

    public static void setDouble(PersistentDataHolder holder, NamespacedKey key, double value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE, value);
    }

    public static void setString(PersistentDataHolder holder, NamespacedKey key, String value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
    }

    public static void setByteArray(PersistentDataHolder holder, NamespacedKey key, byte[] value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.BYTE_ARRAY, value);
    }

    public static void setIntArray(PersistentDataHolder holder, NamespacedKey key, int[] value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.INTEGER_ARRAY, value);
    }

    public static void setLongArray(PersistentDataHolder holder, NamespacedKey key, long[] value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.LONG_ARRAY, value);
    }

    public static void setContainer(PersistentDataHolder holder, NamespacedKey key, PersistentDataContainer value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.TAG_CONTAINER, value);
    }

    /////////////////////////////////////
    // Getters
    /////////////////////////////////////

    /**
     * Get a byte value in a {@link PersistentDataContainer}, if the key doesn't exist it returns -1.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The byte associated with this key or -1 if it doesn't exist
     */
    public static byte getByte(PersistentDataHolder holder, NamespacedKey key) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.BYTE, (byte) -1);
    }

    /**
     * Get a byte value in a {@link PersistentDataContainer} or the default value passed if no key exists.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The byte associated with this key or the default value if it doesn't exist
     */
    public static byte getByte(PersistentDataHolder holder, NamespacedKey key, byte defaultVal) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.BYTE, defaultVal);
    }

    /**
     * Get a short value in a {@link PersistentDataContainer}, if the key doesn't exist it returns -1.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The short associated with this key or -1 if it doesn't exist
     */
    public static short getShort(PersistentDataHolder holder, NamespacedKey key) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.SHORT, (short) -1);
    }

    /**
     * Get a short value in a {@link PersistentDataContainer} or the default value passed if no key exists.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The short associated with this key or the default value if it doesn't exist
     */
    public static short getShort(PersistentDataHolder holder, NamespacedKey key, short defaultVal) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.SHORT, defaultVal);
    }

    /**
     * Get an integer value in a {@link PersistentDataContainer}, if the key doesn't exist it returns -1.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The integer associated with this key or -1 if it doesn't exist
     */
    public static int getInt(PersistentDataHolder holder, NamespacedKey key) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.INTEGER, -1);
    }

    /**
     * Get an integer value in a {@link PersistentDataContainer} or the default value passed if no key exists.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The integer associated with this key or the default value if it doesn't exist
     */
    public static int getInt(PersistentDataHolder holder, NamespacedKey key, int defaultVal) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.INTEGER, defaultVal);
    }

    /**
     * Get a long value in a {@link PersistentDataContainer}, if the key doesn't exist it returns -1.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The long associated with this key or -1 if it doesn't exist
     */
    public static long getLong(PersistentDataHolder holder, NamespacedKey key) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.LONG, (long) -1);
    }

    /**
     * Get a long value in a {@link PersistentDataContainer} or the default value passed if no key exists.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The long associated with this key or the default value if it doesn't exist
     */
    public static long getLong(PersistentDataHolder holder, NamespacedKey key, long defaultVal) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.LONG, defaultVal);
    }

    /**
     * Get a float value in a {@link PersistentDataContainer}, if the key doesn't exist it returns -1.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The float associated with this key or -1 if it doesn't exist
     */
    public static float getFloat(PersistentDataHolder holder, NamespacedKey key) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.FLOAT, (float) -1);
    }

    /**
     * Get a float value in a {@link PersistentDataContainer} or the default value passed if no key exists.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The float associated with this key or the default value if it doesn't exist
     */
    public static float getFloat(PersistentDataHolder holder, NamespacedKey key, float defaultVal) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.FLOAT, defaultVal);
    }

    /**
     * Get a double value in a {@link PersistentDataContainer}, if the key doesn't exist it returns -1.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The double associated with this key or -1 if it doesn't exist
     */
    public static double getDouble(PersistentDataHolder holder, NamespacedKey key) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.DOUBLE, (double) -1);
    }

    /**
     * Get a double value in a {@link PersistentDataContainer} or the default value passed if no key exists.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The double associated with this key or the default value if it doesn't exist
     */
    public static double getDouble(PersistentDataHolder holder, NamespacedKey key, double defaultVal) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.DOUBLE, defaultVal);
    }

    /**
     * Get a String value in a {@link PersistentDataContainer}, if the key doesn't exist it returns null.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The String associated with this key or null if it doesn't exist
     */
    public static String getString(PersistentDataHolder holder, NamespacedKey key) {
        return holder.getPersistentDataContainer().get(key, PersistentDataType.STRING);
    }

    /**
     * Get a String value in a {@link PersistentDataContainer} or the default value passed if no key exists.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The String associated with this key or the default value if it doesn't exist
     */
    public static String getString(PersistentDataHolder holder, NamespacedKey key, String defaultVal) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.STRING, defaultVal);
    }

    /**
     * Get a byte array in a {@link PersistentDataContainer}, if the key doesn't exist it returns null.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The byte array associated with this key or null if it doesn't exist
     */
    public static byte[] getByteArray(PersistentDataHolder holder, NamespacedKey key) {
        return holder.getPersistentDataContainer().get(key, PersistentDataType.BYTE_ARRAY);
    }

    /**
     * Get a byte array in a {@link PersistentDataContainer} or the default value passed if no key exists.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The byte array associated with this key or the default value if it doesn't exist
     */
    public static byte[] getByteArray(PersistentDataHolder holder, NamespacedKey key, byte[] defaultVal) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.BYTE_ARRAY, defaultVal);
    }

    /**
     * Get a integer array in a {@link PersistentDataContainer}, if the key doesn't exist it returns null.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The integer array associated with this key or null if it doesn't exist
     */
    public static int[] getIntArray(PersistentDataHolder holder, NamespacedKey key) {
        return holder.getPersistentDataContainer().get(key, PersistentDataType.INTEGER_ARRAY);
    }

    /**
     * Get a byte array in a {@link PersistentDataContainer} or the default value passed if no key exists.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The byte associated with this key or the default value if it doesn't exist
     */
    public static int[] getIntArray(PersistentDataHolder holder, NamespacedKey key, int[] defaultVal) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.INTEGER_ARRAY, defaultVal);
    }

    /**
     * Get a long array in a {@link PersistentDataContainer}, if the key doesn't exist it returns null.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The long array associated with this key or null if it doesn't exist
     */
    public static long[] getLongArray(PersistentDataHolder holder, NamespacedKey key) {
        return holder.getPersistentDataContainer().get(key, PersistentDataType.LONG_ARRAY);
    }

    /**
     * Get a long array in a {@link PersistentDataContainer} or the default value passed if no key exists.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The long array associated with this key or the default value if it doesn't exist
     */
    public static long[] getLongArray(PersistentDataHolder holder, NamespacedKey key, long[] defaultVal) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.LONG_ARRAY, defaultVal);
    }

    /**
     * Get a nested {@link PersistentDataContainer}, if the key doesn't exist it returns null.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The byte associated with this key or null if it doesn't exist
     */
    public static PersistentDataContainer getContainer(PersistentDataHolder holder, NamespacedKey key) {
        return holder.getPersistentDataContainer().get(key, PersistentDataType.TAG_CONTAINER);
    }

    /**
     * Get a nested {@link PersistentDataContainer} or the default value passed if no key exists.
     *
     * @param holder The {@link PersistentDataHolder} to retrieve the data from
     * @param key The key of the data to retrieve
     * @return The byte associated with this key or the default value if it doesn't exist
     */
    public static PersistentDataContainer getByteArray(PersistentDataHolder holder, NamespacedKey key,
                                                       PersistentDataContainer defaultVal) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.TAG_CONTAINER, defaultVal);
    }

}
