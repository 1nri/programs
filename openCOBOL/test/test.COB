      ******************************************************************
      * Author: Henri Juvonen
      * Date: 13.8.2019
      * Purpose: Testing and Learning Cobol
      * Tectonics: cobc
      ******************************************************************
       IDENTIFICATION DIVISION.
       PROGRAM-ID. FIRST-PROGRAM-OF-HENRI-IN-COBOL.
       DATA DIVISION.
       FILE SECTION.
       WORKING-STORAGE SECTION.
       01 WS-NAME PIC X(15).
       01 WS-TOTAL PIC 9(06).
       01 I PIC 9(1).
       01 WS-B PIC X(6).
       PROCEDURE DIVISION.
       MAIN-PROCEDURE.
            DISPLAY "PRESENT YOUR NAME:"
            MOVE 0 TO WS-TOTAL
            ACCEPT WS-NAME
            DISPLAY "Hello " WS-NAME ", COBOL salutes you"
            COMPUTE WS-TOTAL = 1 + 1.

      *THIS PERFORM SECTION HAS BEEN COPIED AND MODIFIED FROM
      *http://ibmmainframes.com/viewtopic.php?t=35934&highlight=#ixzz5wVwsRvT3
            PERFORM VARYING I FROM 1 BY 1
               UNTIL I > 6
             IF  WS-TOTAL (I : 1) > '0'
                 MOVE WS-TOTAL  (I : )  TO  WS-B
                 MOVE 7              TO  I
                 DISPLAY 'COMPUTED'
             END-IF
            END-PERFORM.
      *THIS IS WHERE THE COPY-MODIFY PART ENDS

            DISPLAY "This program just computed the value " WS-B
            STOP RUN.
       END PROGRAM FIRST-PROGRAM-OF-HENRI-IN-COBOL.
