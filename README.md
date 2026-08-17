# Json Utils

JSON Utils provides a unified and intuitive API for parsing, manipulating, and serializing JSON data in Java applications.
The library abstracts away the complexities of underlying JSON processing implementations, offering
a clean interface for working with JSON objects, arrays, and primitive values across JSON, JSON5, and HJSON formats.
<p>
The library also provides functionality for managing configuration files.

## Features

| Feature                                       | Description                                                                                      |
|-----------------------------------------------|--------------------------------------------------------------------------------------------------|
| **Multi-format support**                      | Parse and serialize JSON, JSON5, and HJSON formats.                                              |
| **Unified data model**                        | Consistent object model (`JModel`, `JObject`, `JArray`, `JValue`) for all supported formats.     |
| **Configuration management**                  | Сonfiguration file utilities (`ConfigFile`, `OneParameterConfigFile`, `ParameterMapConfigFile`). |
| **Reader/Writer abstractions**                | Separate reading and writing components for flexible I/O operations.                             |
| **Lightweight**                               | The core has zero runtime dependencies – you only add the JSON library you actually use.         |
| **JType‑based serialization/deserialization** | Flexible mapping of your project’s custom objects to and from JSON using the `JType` system.     |

## Installation

### Maven
```xml
<repositories>
    <repository>
        <id>ferra13671-maven</id>
        <url>https://ferra13671.github.io/maven/</url>
    </repository>
</repositories>
<dependency>
<groupId>com.ferra13671</groupId>
<artifactId>json-utils</artifactId>
<version>VERSION</version>
</dependency>
```

### Gradle
```groovy
repositories {
    maven {
        name = "ferra13671-maven"
        url = 'https://ferra13671.github.io/maven/'
    }
}

dependencies {
    implementation 'com.ferra13671:json-utils:VERSION'
}
```

### Optional format libraries

You just add the library that supports the format you need:

| Format    | Library               |
|-----------|-----------------------|
| **JSON**  | com.google.code:gson  |
| **JSON5** | de.marhali:json5-java |
| **HJSON** | org.hjson:hjson       |