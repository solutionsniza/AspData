package in.izba.aspdata;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class AddData extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;


    RadioGroup radioGroup;

    EditText txtDate, txtTime, mobileNum;
    EditText readText;
    TextView userEmailId;

    private int year, month, day, mHour, mMinute;
    //static final int DIALOG_ID=0;

    Button btnDatePicker, btnTimePicker;

    String genderText, usrEmailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(AddData.this, MainActivity.class));
                }
            }
        };

        //String usrEmailId = mAuth.getCurrentUser().toString();
        Intent intent = getIntent();
        usrEmailId = intent.getStringExtra("usrEmail");
        userEmailId = (TextView) findViewById(R.id.userEmail);
        userEmailId.setText(usrEmailId);

        //Toast.makeText(AddData.this, "User email id: " + usrEmailId, Toast.LENGTH_SHORT).show();


        radioGroup = (RadioGroup) findViewById(R.id.genderSelection);
        radioGroup.clearCheck();

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);

        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);
        mobileNum=(EditText)findViewById(R.id.mobileNumber);

        java.util.Calendar c = java.util.Calendar.getInstance();
        year = c.get(java.util.Calendar.YEAR);
        month = c.get(java.util.Calendar.MONTH);
        day = c.get(java.util.Calendar.DATE);

        month += 1;
        txtDate.setText(day + " / " + month + " / " + year);


        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        txtTime.setText(mHour + ":" + mMinute);

        // DATE PICKER SELECTION DIALOG
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DatePickerDialog datePickerDialog = new DatePickerDialog(AddData.this, new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker datePicker, int yearOfYear, int monthOfDay, int dayOfMonth) {
                       dayOfMonth += 1;
                        txtDate.setText(dayOfMonth + "/" + monthOfDay + "/" + yearOfYear);
                   }
               }, year, month, day);
                datePickerDialog.show();
            }
        });
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddData.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yearOfYear, int monthOfDay, int dayOfMonth) {
                        monthOfDay += 1;
                        txtDate.setText(dayOfMonth + "/" + monthOfDay + "/" + yearOfYear);
                    }
                }, year, month-1, day);
                datePickerDialog.show();
            }
        });

        //TIME PICKER DIALOG SELECTION
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddData.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfDay) {
                        txtTime.setText(hourOfDay + ":" + minuteOfDay);
                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddData.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfDay) {
                        txtTime.setText(hourOfDay + ":" + minuteOfDay);
                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });




        Button button = (Button) findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the checked Radio Button ID from Radio Group
                final int selectedRadioButtonID = radioGroup.getCheckedRadioButtonId();
                // If nothing is selected from Radio Group, then it return -1
                if (selectedRadioButtonID != -1) {

                    RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);
                    genderText = selectedRadioButton.getText().toString();

                    //Toast.makeText(AddData.this, selectedRadioButtonText, Toast.LENGTH_SHORT).show();
                }
                else{
                    genderText = "Gender not selected.";
                    //Toast.makeText(AddData.this, "Nothing selected from Radio Group.", Toast.LENGTH_SHORT).show();
                }


                readText = (EditText) findViewById(R.id.userName);
                String getUserName = readText.getText().toString();

                readText = (EditText) findViewById(R.id.userSurName);
                String getUserSurName = readText.getText().toString();

                readText = (EditText) findViewById(R.id.placeOfBirth);
                String birthCity = readText.getText().toString();

                String dateOfBirth = txtDate.getText().toString();
                String timeOfBirth = txtTime.getText().toString();

                readText = (EditText) findViewById(R.id.starAndPadam);
                String starPadam = readText.getText().toString();

                readText = (EditText) findViewById(R.id.height);
                String usrHeight = readText.getText().toString();

                readText = (EditText) findViewById(R.id.maritalStatus);
                String marStatus = readText.getText().toString();

                readText = (EditText) findViewById(R.id.complexion);
                String compColor = readText.getText().toString();

                readText = (EditText) findViewById(R.id.fatherName);
                String fName = readText.getText().toString();

                readText = (EditText) findViewById(R.id.motherName);
                String mName = readText.getText().toString();

                readText = (EditText) findViewById(R.id.caste);
                String cSubCaste = readText.getText().toString();

                readText = (EditText) findViewById(R.id.gothram);
                String gthrm = readText.getText().toString();

                /*readText = (EditText) findViewById(R.id.userEmail);
                String contactEmail = readText.getText().toString();*/

                readText = (EditText) findViewById(R.id.education);
                String eduQual = readText.getText().toString();

                readText = (EditText) findViewById(R.id.jobInfo);
                String empDetails = readText.getText().toString();

                readText = (EditText) findViewById(R.id.businessInfo);
                String busInfo = readText.getText().toString();

                readText = (EditText) findViewById(R.id.hobbies);
                String usrHobbies = readText.getText().toString();

                readText = (EditText) findViewById(R.id.foodHabits);
                String vegNonVeg = readText.getText().toString();

                readText = (EditText) findViewById(R.id.settledAt);
                String preLoc = readText.getText().toString();

                readText = (EditText) findViewById(R.id.partnerPref);
                String partPref = readText.getText().toString();

                readText = (EditText) findViewById(R.id.aboutFamily);
                String abtFamily = readText.getText().toString();

                readText = (EditText) findViewById(R.id.assets);
                String usrAssets = readText.getText().toString();

                readText = (EditText) findViewById(R.id.brotherSister);
                String siblingInfo = readText.getText().toString();

                readText = (EditText) findViewById(R.id.address);
                String comAddress = readText.getText().toString();

                readText = (EditText) findViewById(R.id.references);
                String usrRef = readText.getText().toString();

                readText = (EditText) findViewById(R.id.mobileNumber);
                String mobNum = readText.getText().toString();

                readText = (EditText) findViewById(R.id.altMobile);
                String altMobNum = readText.getText().toString();


                //MOBILE NUMBER CHECK
                String mobileNumber = mobileNum.getText().toString();
                if(mobileNumber.isEmpty()) {
                    showDialog("Please Enter Valid Mobile Number");
                    //Toast.makeText(AddData.this, "Text field is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    int lenght = mobileNumber.length();
                    if(lenght == 10) Toast.makeText(AddData.this, mobileNumber, Toast.LENGTH_SHORT).show();
                    String messageContent = "To," + "\n" + "ASP Matrimonial Services," + "\n"
                            + "Gender : " + genderText + "\n"
                            + "Name : " + getUserName + "\n"
                            + "SurName : " + getUserSurName + "\n"
                            + "Date of Birth : " + dateOfBirth + "\n"
                            + "Time of Birth : " + timeOfBirth + "\n"
                            + "Place of Birth : " + birthCity + "\n"
                            + "Star and Padam : " + starPadam + "\n"
                            + "Height : " + usrHeight + "\n"
                            + "Marital Status : " + marStatus + "\n"
                            + "Complexion : " + compColor + "\n"
                            + "Fathers name and Details : " + fName + "\n"
                            + "Mothers name and Details : " + mName + "\n"
                            + "Caste and Sub Caste : " + cSubCaste + "\n"
                            + "Gothram : " + gthrm + "\n"
                            + "Contact Email ID : " + usrEmailId + "\n"
                            + "Educational Qualifications : " + eduQual + "\n"
                            + "Employment Details : " + empDetails + "\n"
                            + "Business Details : " + busInfo + "\n"
                            + "Hobbies : " + usrHobbies + "\n"
                            + "Veg or Non-Veg : " + vegNonVeg + "\n"
                            + "Presently Settled At : " + preLoc + "\n"
                            + "Partner Preference : " + partPref + "\n"
                            + "About Family : " + abtFamily + "\n"
                            + "Assets : " + usrAssets + "\n"
                            + "About Brothers and Sisters : " + siblingInfo + "\n"
                            + "Address for Communication : " + comAddress + "\n"
                            + "References : " + usrRef + "\n"
                            + "Mobile Number : " + mobNum + "\n"
                            + "Alternate Mobile Number : " + altMobNum + "\n"
                            + "The information provided above is correct to my knowledge."
                            ;
                    sendEmail(messageContent);
                    Toast.makeText(AddData.this, "after email", Toast.LENGTH_SHORT).show();
                    /*Intent thankIntent = new Intent(AddData.this, ThankYou.class);
                    startActivity(thankIntent);*/
                }
            }
        });

    }


    // Adding action toolbar to the application
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_menu, menu);
        setTitle("Asp Matrimony Services");
        return true;
    }

    // ACTION BAR MENU ITEMS AND ON CLICK
    // on menu item selected action bar menu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.contactUs:
                showDialog("Asp Matrimony Services" + "\n" + "asdsd@gmail.com");
                return true;

            case R.id.aboutUs:
                String message = "Asp Matrimony Services " +
                        "Matrimony service for all.";
                showDialog(message);
                return true;

            case R.id.about:
                showDialog("Beta Version 1.1.0");
                return true;

            case R.id.disclaimer:
                showDialog("Copyright Niza Solutions. Email: solutionsniza@gmail.com" + "\n" +
                "All the content belongs to the ASP Matrimony Services, and is sole responsible.");
                return true;

            case R.id.exit:
                mAuth.signOut();
                super.onBackPressed();
                return true;

            default:
                showDialog("Please try again...");
                return super.onOptionsItemSelected(item);

        }
    }

    public void showDialog(String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(AddData.this);
        dialog.setCancelable(false);
        dialog.setTitle("ASP Matrimony Services");

        dialog.setMessage(message);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
                dialog.dismiss();
            }
        });
        AlertDialog builder = dialog.create();
        builder.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

    }

    protected void sendEmail(String mesContent) {
        String mailSubject = "Application for Matrimony Service";
        String mailBody = mesContent;

        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setData(Uri.parse("mailto:"));

        String [] to={"soanker80@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_SUBJECT, mailSubject);
        intent.putExtra(Intent.EXTRA_TEXT, mailBody);

        intent.setType("message/rfc822");
        Intent chooser = Intent.createChooser(intent, "send email");
        startActivityForResult(chooser, 2);
    }

    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            String thanksMessage = "ASP Matrimonial" + "\n" + "Thanks for your details." + "\n" + "Our team will communicate with you in a while."
                    + "\n" + "Feel free to reach out to us for any details."  + "\n" + "Do share your Latest Coloured photograph over email."
                    + "\n" + "If you are willing to pay Rs.1000/- and get the benefits of Premium Member, Please click the link below."
                    + "\n" + "https://www.payumoney.com/paybypayumoney/#/275193"
                    + "\n" + "Regards"
                    + "\n" + "ASP Matrimony";
            showDialog(thanksMessage);
        }
    }
}
