
AppTitle "Screensaver Tutorial1"    ; The Name of your Screensaver

ChangeDir SystemProperty$("appdir") ; IMPORTANT
       
Global tank1 = LoadImage("tank1.bmp")
Global tank2 = LoadImage("tank2.bmp")

Global one = LoadAnimImage("fish 1.bmp",62,25,0,2)
Global two = LoadAnimImage("fish 2.bmp",72,20,0,2)
Global three = LoadAnimImage("fish 3.bmp",88,61,0,2)

Global onex#=100
Global oney#=100
Global onemx#=Rnd(-2,2)
Global onemy#=Rnd(-2,2)
Global oneframe = 1

Global twox#=400
Global twoy#=350
Global twomx#=Rnd(-2,2)
Global twomy#=Rnd(-2,2)
Global twoframe = 0

Global threex#=200
Global threey#=300
Global threemx#=Rnd(-2,2)
Global threemy#=Rnd(-2,2)
Global threeframe = 1

Global tank = Rnd(1,2)


                             ; Needed to run your Screensaver under Windows!!!.
                                    ; It changes the runtime Dir to where the Screensaver is located.
                                    ; Disable it while testing, because "appdir" of Blitz is "\bin".
                                    ; else your program might just crash!!! :)


; Checking Parameters that Windows uses for handling Screensavers 

If CommandLine$() <> "" Then                                ; If Parameter is present then 
  If Upper(Left$(CommandLine$(),2)) = "/C" Then Configure() ;  check if Screensaver Configuration shall be executed
  If Upper(Left$(CommandLine$(),2)) = "/S" Then Start()     ;  or Screensaver itself should be started  
EndIf ; <> ""

End   ; End program... bye bye 


Function Configure()
; -------------------------------------------------------------------------------------------- 
; |                                                                                          |  
; | Insert Code for your Configuration Screen.                                               |  
; | If needed, execute an external application. Written in VB, Delphi or whatever.           |
; |                                                                                          |  
; -------------------------------------------------------------------------------------------- 

; Display of Confguration Options such as: Resolution, Detail Level etc.

End Function                       ; of Configure()



Function Start()
; -------------------------------------------------------------------------------------------- 
; |                                                                                          |  
; | Insert Code for your Screensaver program                                                 |  
; |                                                                                          |  
; -------------------------------------------------------------------------------------------- 

    ; Set GFX-Mode and load Data... (placed before main loop!)
 
	FlushKeys()                   ; clean keyboardbuffer
	FlushMouse()                  ; clean mousebuffer  
	MoveMouse 0,0                 ; move mouse to 0,0 for later check



AppTitle "Computer fish tank"
Graphics 640,480,0,1
SetBuffer BackBuffer()
SeedRnd MilliSecs()
AutoMidHandle True

	
	Repeat                        ; Main loop for your Screensaver Code 

testkeys()
drawstuff()
movefish()
choosdirection()
checkcollision()
changedirection()

       Delay 1                       ; secure that the CPU usage is not 100% all time!	 	
	Until GetMouse() <> 0 Or MouseX() <> 0 Or MouseY() <> 0 Or GetKey() <> 0 ; check if user makes a move :)

End Function                      ; of Start() 




Function testkeys()
If KeyHit(2)
tank = 1
EndIf 
If KeyHit(3)
tank = 2
EndIf 
End Function 


Function drawstuff()

If tank =1
DrawImage tank1,320,240
EndIf 
If tank = 2
DrawImage tank2,320,240
EndIf  

DrawImage one,onex,oney,oneframe
DrawImage two,twox,twoy,twoframe
DrawImage three,threex,threey,threeframe
End Function 

Function movefish()

onex = onex + onemx
oney = oney + onemy

If onemx = 0
  onemx =1
EndIf 
If onemy = 0
  onemy =-1
EndIf 



twox = twox + twomx
twoy = twoy + twomy

If twomx = 0
  twomy = -2
EndIf 
If twomy = 0
  twomy =2
EndIf 


threex = threex + threemx
threey = threey + threemy

If threemx = 0
 threemx =2
EndIf 
If threemy = 0
threemy =1
EndIf 

End Function 


Function choosdirection()

If onemx < 0
  oneframe = 1
Else
  oneframe = 0
EndIf 



If twomx < 0
  twoframe = 1
Else
  twoframe = 0
EndIf 


If threemx < 0 
  threeframe = 1
Else
  threeframe = 0
EndIf 

End Function  


Function checkcollision()
 
;fish one  (the gold one)
If onex < 31 Or onex >609
  onemx = -onemx
EndIf 

If oney < 15 Or oney > 465
  onemy = - onemy
EndIf 

;fish two (the swordtail)

If twox < 36 Or twox>449
   twomx = -twomx
EndIf 

If twoy <10 Or twoy>470
  twomy = - twomy
EndIf 

;fish three (the fat one)

If threex < 44 Or threex >596
  threemx = -threemx
EndIf 

If threey < 30 Or threey>450
  threemy = - threemy
EndIf 

End Function 

Function changedirection()
SeedRnd MilliSecs()

changeone = Rnd(200)
If changeone = 4
   onemx = - onemx
EndIf 

If changeone = 15
   onemy = -onemy
EndIf 

If onemx < 0
 onemx = Rnd(-2,-1)
EndIf 
If onemx > 0
  onemx = Rnd(1,2)
EndIf 



changetwo = Rnd(200)
If changetwo = 3
  twomx = -twomx
EndIf 

If changetwo = Rnd(11)
  twomy = -twomy
EndIf 

If twomx < 0
 twomx = Rnd(-2,-1)
EndIf 
If twomx > 0
  twomx = Rnd(1,2)
EndIf 



changethree =Rnd(200)
If changethree = 9
   threemx = -threemx
EndIf 

If changethree = 18
  threemy = - threemy
EndIf 

If threemx < 0
 threemx = Rnd(-2,-1)
EndIf 
If threemx > 0
  threemx = Rnd(1,2)
EndIf 


End Function 