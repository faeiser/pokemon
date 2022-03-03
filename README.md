# Aufgabe: Pokemon
Der Spieler kann zu Beginn des Spiels ein Pokémon durch Angabe der Index-Nummer oder dem Namen des Pokémons wählen, dieses wird für den Kampf ausgewählt.

Gib auf der Konsole aus, um welches Pokemon es sich handelt und seine ASCII-Art Repräsentierung - falls es keine gibt, kannst du ein Fallback ASCII art einfügen ;) Das gegnerische Pokemon wählt der Computer zufällig.

Die Pokémon sollen aus einer CSV Datei importiert werden (händisches Erfassen ist nicht notwendig). Mögliche Quelle: <https://gist.github.com/armgilles/194bcff35001e7eb53a2a8b441e8b2c6> (Andere Versionen sind natürlich auch möglich.)

## Kampf
Zu Beginn des Kampfs, werden dem Spieler die Stats seines Pokémons ausgegeben. Das könnte z.B so aussehen:

Bulbasaur (80/100 ♥)  Squirtle (100/100 ♥)
(1) Overgrow  Torrent
(2) Chlorophyll  Rain Dish
Bulbasaur ist dabei der Name des Pokémons. 80 seine aktuelle Gesundheit, 100 ist die maximale Gesundheit (HP google sheet) Overgrow und Chlorophyll sind dabei die beiden möglichen Attacken (abilities).

Der Benutzer kann dann durch Angabe einer Zahl spezifizieren welche Attacke ausgewählt werden soll (1 oder 2 in unserem Fall). Bei einer fehlerhaften Angabe, solltest du den Benuter auffordern eine existierende Attacke zu wählen.

Auf der Konsole solltest du dann ausgeben welche Attacke gewählt wurde:

    Bulbasur attacks with Overgrow. Bulbasur deals X damage.

Dann sollte Squirtles health um X verringert werden.

## Schadensberechnung
Der Schaden den das Pokemon anrichtet errechnet sich aus dem `attack` Wert des angreifendes Pokemons multipliziert mit einem zufälligen Modifikator (zwischen 0 und 1). Natürlich verteidigt sich das gegnerische Pokemon aus - der Verteidigungswert errechnet sich aus dem `defense` Wert des verteidigendes Pokemons multipliziert mit einem zufälligen Modifikator (zwischen 0 und 0.5).

Wenn nun der Angriffswert - Verteidigungswert positiv ist, wird dieser Wert von den HP jenes Pokemons abgezogen, dass angegriffen wird.

Beispiel: Bulbasurs hat einen Angriffswert von 48. Squirtle eine verteidigung von 65. Der zufällige Angriffsmodifikator ist 0.7, der Verteidigungsmodifikator 0.1.

    0.7*48 - 0.1*65 = 27.1
Also wird bei dieser Attacke Squirtle 27 HP verlieren.

Das gegnerische Pokemon wählt seine Attacke zufällig.

Das Pokemon dass zu erst 0 oder weniger HP erreicht verliert.
