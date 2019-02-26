# DBIntern

AlertDialog Button Color verändern:

hinter setButton() und vor show() folgendes einfügen:

               alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorDeutscheBahn, null));
                    }
                });

Stell sicher, dass alle AlertDialogs, bei denen du das anwendest FINAL sind.
Ich hab erstmal das normale rot ausgewählt, wenn du die Farbe ändern willst einfach R.color.xyz...
