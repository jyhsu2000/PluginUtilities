# PluginUtilities
[![](https://jitpack.io/v/jyhsu2000/PluginUtilities.svg)](https://jitpack.io/#jyhsu2000/PluginUtilities)

Utilities for Bukkit/Spigot plugin

# Install

**Step 1.** Add the JitPack repository to your build file
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

**Step 2.** Add the dependency
```xml
<dependency>
    <groupId>com.github.jyhsu2000</groupId>
    <artifactId>PluginUtilities</artifactId>
    <version>master-SNAPSHOT</version>
</dependency>
```

**Step 3.** Add the shading plugin to the build.  
Replace `YOUR.OWN.PACKAGE` with your own package.
```xml
<build>
     <plugins>
         <plugin>
             <groupId>org.apache.maven.plugins</groupId>
             <artifactId>maven-shade-plugin</artifactId>
             <version>2.3</version>
             <configuration>
                 <artifactSet>
                     <includes>
                         <include>com.github.jyhsu2000:PluginUtilities</include>
                     </includes>
                 </artifactSet>
                 <relocations>
                     <relocation>
                         <pattern>club.kid7.PluginUtilities</pattern>
                         <shadedPattern>YOUR.OWN.PACKAGE</shadedPattern>
                     </relocation>
                 </relocations>
             </configuration>
             <executions>
                 <execution>
                     <phase>package</phase>
                     <goals>
                         <goal>shade</goal>
                     </goals>
                 </execution>
             </executions>
         </plugin>
     </plugins>
 </build>
```
