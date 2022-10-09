FoodCompass

a)
Diese Anwendung soll Nutzern helfen sich bewusster und gesünder zu ernähren. Dabei können die gegessenen Lebensmittel eingetragen werden und die Anwendung zeigt anschließende mithilfe eines Tachos an in welchem Bereich, von gesund bis ungesund, man sich befindet. Mit jeder Mahlzeit kann sich dieser Tachostand verändern. Dabei werden die Nährstoffe, Kalorien und „Gesundheit“ der Lebensmittel als Eckdaten verwendet, um diese zu kategorisieren. Die App spricht somit Menschen jeder Altersklasse an, welche sich gesund beziehungsweise bewusster ernähren wollen
b)Intendierte Features/Ziele:
- Tacho Anzeige durch eine Custom View um zu zeigen, ob man sich momentan gesund oder ungesund ernährt
- Notifications, um den Nutzer daran zu erinnen essen immer wieder einzutragen (wurde nicht umgesetzt)
- Datenbank
- Resposive Layout
- Barcode scannen, um Lebensmittel automatisch einzutragen
- http Schnittstelle (API-Request)
- Eigener Adapter

c/d)
Beim Öffnen der App wird ein Tacho angezeigt, welcher durch eine Custom View erstellt worden ist. Wenn man weiter herunterscrollt, kann man sein Essen eintragen in dem man auf das Feld für die jeweilige Mahlzeit drückt. Die Lebensmittel können in einer Suchleiste gesucht werden und anschließend erfolgt ein API-Request, welcher passende Ergebnisse liefert. Durch das Klicken auf ein bestimmtes Lebensmittel wir ein neues Fenster (DetailDialogueFragment) geöffnet und die Nährstoffe werden angezeigt. Anschließend kann das Lebensmittel hinzugefügt werden und die Daten werden in einer Datenbank gespeichert. Mit dem Hinzufügen eines Lebensmittels sollte sich auch der Tachostand verändern. Der Richtlinienwert ist dabei der Nutriscore, welcher aus der API ausgelesen wird. Das Eintragen der Lebnsmittel soll zudem auch durch das Scannen des Barcodes ermöglicht werden und ein Kalender welcher die Tage farblich makiert zeigt soll das Essverhalten über einen größeren Zeitraum wiederspiegeln.

Der Kalender und der Barcodescanner wurden nur teilweise oder gar nicht umgestetzt, der Tachostand kann sich zudem auch nciht verändern.

(Ein Video hat leider nicht geklappt, da Codeteile nicht vollständig sind)
Der CustomView Tacho:
![Screenshot (28)](https://user-images.githubusercontent.com/76117353/194769081-a0633e3e-7eca-411b-a55a-317b1d58287c.png)

Auswahlmöglichkeit der Mahlzeit:


Das DetailDialogueFragement:
![Screenshot (29)](https://user-images.githubusercontent.com/76117353/194769223-7f0f250f-d601-4cf4-a7bc-a4a71bdea6da.png)

Das Fenster um Essen zu suchen:
![Screenshot (30)](https://user-images.githubusercontent.com/76117353/194769299-c01e3910-85d8-464f-9395-daf60f5a8420.png)

e) Aufgabenverteilung:

-Viktoria Stasinski:

Hat sich um die komplette API-Abfrage gekümmert, sowie die Verbinung zwischen den Daten der API-Abfrage und dem UI. 
Klassen und Activitys welche von ihr erstellt worden sind:
- FoodDataRequest
- FoodObject
- Meal
- FoodAddActivity
- DetailDialogueFragment
- In der MainActivity: alles außer der BarcodeScanner-Code

-Deza Realdy:

Hat sich um das komplette UI gekümmert und um alle layout-Dateien im res-Ordner.
Zusätzlich hat Deza den Teil des Codes für den Barcodescanner geschrieben.

-Johannes Trisch:

Hat sich um die Datenbank gekümmert.
Alle Klassen im db-Ordner sind von ihm

f)https://de.openfoodfacts.org/


