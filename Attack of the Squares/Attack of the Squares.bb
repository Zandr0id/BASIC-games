AppTitle "Attack of the Squares"
Graphics 640,480


;set up variables
Global highscore = highscore1
Global scrolly=0
Global x=230
Global y=20
Global score = 0
Global time = CreateTimer(20) 
;image variables
Global greensquare = CreateImage(30,30)
Global redsquare = CreateImage(20,20)
Global stars = CreateImage(45,45)
; the one sound
bounce = LoadSound("badshot.wav")
die = LoadSound("playerexplode.wav")
;set up types
Type square
    Field x,y
	Field Yvolocity
	Field Xvolocity
End Type


SetBuffer ImageBuffer(greensquare)
Color 0,255,0
Rect 0,0,30,30,1

SetBuffer ImageBuffer(redsquare)
Color 255,0,0
Rect 0,0,20,20,0

Color 255,255,255


; make a stary background
 

SetBuffer ImageBuffer(stars)
SeedRnd MilliSecs()
For plots = 1 To 10
Plot Rnd(1,45),Rnd(1,45)
Next 

;put the buffer back 
SetBuffer BackBuffer()

makesquares()
makelevel()
While Not KeyDown(1)
Cls 
makestars()

drawsquares()
testkeys()
drawHUD()
moveevilsquares()
checkcollision()
score = score +1
Flip
Wend


Function makesquares()
SeedRnd MilliSecs()


For makebads = 1 To 30

badguy.square = New square
badguy\x = Rnd(1,570)
badguy\y = Rnd(100,450)
badguy\Xvolocity=Rnd(-3,3)
badguy\Yvolocity=Rnd(-3,3)

If badguy\Xvolocity = 0 Then badguy\Xvolocity = -2
If badguy\Yvolocity = 0 Then badguy\Yvolocity = 1

Delay 50
Next 

Text 320,240,"Ready...Set...",1,1
Flip 
Delay 2000
Text 320,250,"GO!!!",1,1
Flip 
Delay 1000

End Function 

Function makelevel()

For badguy.square = Each square
badguy\x = Rnd(1,570)
badguy\y = Rnd(100,450)
badguy\Xvolocity=Rnd(-2,2)
badguy\Yvolocity=Rnd(-2,2)
Next 

x = 230
y = 20 
score = 0

End Function 

Function makestars()

TileImage stars ,0,scrolly
scrolly = scrolly + 2

If scrolly > 45
scrolly = 0
EndIf 

End Function 



Function drawsquares()

Color 0,255,0
DrawImage greensquare,x,y

Color 255,0,0
For badguy.square = Each square
DrawImage redsquare,badguy\x,badguy\y
Next 

Color 255,255,255

End Function 

Function testkeys()
If KeyDown(203)
   x=x-2
    If x <=0
       x=0
	EndIf 
End If 

If KeyDown(205)
   x=x+2
    If x >=610
       x=610
	EndIf 
End If 

If KeyDown(200)
   y=y-2
    If y <=0
       y=0
	EndIf 
End If 

If KeyDown(208)
   y=y+2
    If y >=450
       y=450
	EndIf 
End If 

If KeyHit(1)
End
EndIf 


End Function 


Function moveevilsquares()

For badguy.square = Each square
   badguy\x = badguy\x + badguy\Xvolocity
   badguy\y = badguy\y + badguy\Yvolocity
	
	If badguy\x >= 620 Or badguy\x <=0 Then
	    PlaySound bounce 
	badguy\Xvolocity = -badguy\Xvolocity
	 
	If badguy\Xvolocity < 0 Then
	     badguy\Xvolocity = badguy\Xvolocity -.4
	   EndIf 
	
	If badguy\Xvolocity > 0 Then
	     badguy\Xvolocity = badguy\Xvolocity -.4
	   EndIf 
	
	
	EndIf 

	If badguy\y >= 460 Or badguy\y <=0 Then
	   PlaySound bounce
	badguy\Yvolocity = -badguy\Yvolocity
	     
	
	   If badguy\Yvolocity > 0 Then
	     badguy\Yvolocity = badguy\Yvolocity +.4
	   EndIf 
	   If badguy\Yvolocity < 0 Then
	     badguy\Yvolocity = badguy\Yvolocity -.4
	   EndIf 
	
	EndIf
Next 

End Function 

Function drawHUD()

Text 0,0,"Score: " + score
If score < highscore Then 
Text 0,10,"Highscore: " + highscore
Else
Text 0,10,"Highscore: " + score
EndIf 
WaitTimer(time)

End Function 


Function checkcollision()
 
 For badguy.square=Each square 
   If ImagesCollide(redsquare,badguy\x,badguy\y,0,greensquare,x,y,0) Then     
   Delay 50
    Cls
    Text 320,240,"You got hit!!!",1,1
    Flip 
    Delay 2000
   End 
    EndIf 
  Next 

End Function 
 