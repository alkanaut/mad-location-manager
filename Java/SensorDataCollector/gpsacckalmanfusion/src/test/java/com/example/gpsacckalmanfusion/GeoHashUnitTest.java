package com.example.gpsacckalmanfusion;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by lezh1k on 2/13/18.
 */

public class GeoHashUnitTest {
    class EncodeTestItem {
        double lat;
        double lon;
        int precision;
        String expected;

        EncodeTestItem(double lat, double lon, int precision, String expected) {
            this.lat = lat;
            this.lon = lon;
            this.precision = precision;
            this.expected = expected;
        }
    }

    @Test
    public void encodeTest() throws Exception {
        EncodeTestItem posTests[] = {
                new EncodeTestItem(44.87533558, -64.3057251, 8, "dxfr29mc"),
                new EncodeTestItem(46.76244305, -60.6362915, 8, "f8kfh0y4"),
                new EncodeTestItem(50.79204706, 61.47949219, 8, "v358zn2j"),
                new EncodeTestItem(-82.214234, 114.257834, 9, "n93k21252"),
                new EncodeTestItem(-21.45306863, 137.02148438, 9, "rh1myn84b"),
                new EncodeTestItem(44.87533558, -64.3057251, 7, "dxfr29m"),
                new EncodeTestItem(46.76244305, -60.6362915, 7, "f8kfh0y"),
                new EncodeTestItem(50.79204706, 61.47949219, 7, "v358zn2"),
                new EncodeTestItem(-82.214234, 114.257834, 8, "n93k2125"),
                new EncodeTestItem(-21.45306863, 137.02148438, 8, "rh1myn84"),
        };

        //random changes in right strings
        EncodeTestItem negTests[] = {
                new EncodeTestItem(44.87533558, -64.3057251, 8, "dxer29mc"),
                new EncodeTestItem(46.76244305, -60.6362915, 8, "f8kgh0y4"),
                new EncodeTestItem(50.79204706, 61.47949219, 8, "v338zn2j"),
                new EncodeTestItem(-82.214234, 114.257834, 9, "n93kgg1252"),
                new EncodeTestItem(-21.45306863, 137.02148438, 9, "rh12myn84b"),
                new EncodeTestItem(44.87533558, -64.3057251, 7, "dxfr2gm"),
                new EncodeTestItem(46.76244305, -60.6362915, 7, "f84fh0y"),
                new EncodeTestItem(50.79204706, 61.47949219, 7, "v318zn2"),
                new EncodeTestItem(-82.214234, 114.257834, 8, "143k2125"),
                new EncodeTestItem(-21.45306863, 137.02148438, 8, "43fmyn84"),
        };

        for (EncodeTestItem ti : posTests) {
            char buff[] = new char[ti.precision];
            GeoHash.encode(ti.lat, ti.lon, buff, ti.precision);
            assertTrue(Arrays.equals(ti.expected.toCharArray(), buff));
        }

        for (EncodeTestItem ti : negTests) {
            char buff[] = new char[ti.precision];
            GeoHash.encode(ti.lat, ti.lon, buff, ti.precision);
            assertFalse(Arrays.equals(ti.expected.toCharArray(), buff));
        }
    }

    @Test
    public void equationTest() throws Exception {
        GeoPoint geo1 = new GeoPoint(44.87533558, 72.5656988);
        GeoPoint geo2 = new GeoPoint(44.87533558, 72.5656988);
        GeoPoint geo3 = new GeoPoint(44.87533540, 72.5656988);

        final int prec = GeoHash.GEOHASH_MAX_PRECISION;
        char buff1[] = new char[prec];
        char buff2[] = new char[prec];
        char buff3[] = new char[prec];

        GeoHash.encode(geo1.Latitude, geo1.Longitude, buff1, prec);
        GeoHash.encode(geo2.Latitude, geo2.Longitude, buff2, prec);
        GeoHash.encode(geo3.Latitude, geo3.Longitude, buff3, prec);

        assertTrue(Arrays.equals(buff1, buff2));
        assertFalse(Arrays.equals(buff1, buff3));
    }
}
