/*
* Copyright (c) 2008-2018, Harald Walker (bitwalker.eu) and contributing developers 
* All rights reserved.
* 
* Redistribution and use in source and binary forms, with or
* without modification, are permitted provided that the
* following conditions are met:
* 
* * Redistributions of source code must retain the above
* copyright notice, this list of conditions and the following
* disclaimer.
* 
* * Redistributions in binary form must reproduce the above
* copyright notice, this list of conditions and the following
* disclaimer in the documentation and/or other materials
* provided with the distribution.
* 
* * Neither the name of bitwalker nor the names of its
* contributors may be used to endorse or promote products
* derived from this software without specific prior written
* permission.
* 
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
* CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
* INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
* MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
* SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
* NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
* HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
* CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
* OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package eu.bitwalker.useragentutils;

/**
 * Container for general version information. All version information is stored
 * as String as sometimes version information includes alphabetical characters.
 *
 * @author harald
 */
public class Version implements Comparable<Version> {

    String version;
    String majorVersion;
    String minorVersion;

    /**
     * This constructor is created for APIs that require default constructor and
     * should never be used directly.
     *
     * @deprecated Use {@link #Version(String, String, String)}
     */
    @Deprecated
    public Version() {
        // default constructor for APIs that require it (e.g. JSON serialization)
    }

    public Version(String version, String majorVersion, String minorVersion) {
        super();
        this.version = version;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
    }

    public String getVersion() {
        return version;
    }

    public String getMajorVersion() {
        return majorVersion;
    }

    public String getMinorVersion() {
        return minorVersion;
    }

    @Override
    public String toString() {
        return version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((majorVersion == null) ? 0 : majorVersion.hashCode());
        result = prime * result
                + ((minorVersion == null) ? 0 : minorVersion.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Version other = (Version) obj;
        if (majorVersion == null) {
            if (other.majorVersion != null) {
                return false;
            }
        } else if (!majorVersion.equals(other.majorVersion)) {
            return false;
        }
        if (minorVersion == null) {
            if (other.minorVersion != null) {
                return false;
            }
        } else if (!minorVersion.equals(other.minorVersion)) {
            return false;
        }
        if (version == null) {
            if (other.version != null) {
                return false;
            }
        } else if (!version.equals(other.version)) {
            return false;
        }
        return true;
    }

    public int compareTo(Version other) {
        if (other == null) {
            return 1;
        }

        String[] versionParts = version.split("\\.");
        String[] otherVersionParts = other.version.split("\\.");

        for (int i = 0; i < Math.min(versionParts.length, otherVersionParts.length); i++) {
            if (versionParts[i].length() == otherVersionParts[i].length()) {
                int comparisonResult = versionParts[i].compareTo(otherVersionParts[i]);
                if (comparisonResult == 0) {
                    continue;
                } else {
                    return comparisonResult;
                }
            } else {
                return versionParts[i].length() > otherVersionParts[i].length() ? 1 : -1;
            }
        }

        if (versionParts.length > otherVersionParts.length) {
            return 1;
        } else if (versionParts.length < otherVersionParts.length) {
            return -1;
        } else {
            return 0;
        }
    }
}
