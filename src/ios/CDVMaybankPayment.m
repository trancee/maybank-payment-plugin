#import "CDVMaybankPayment.h"

@implementation CDVMaybankPayment

+ (void)initialize
{
}

- (void)start:(CDVInvokedUrlCommand*)command
{
	NSLog(@">> start");
	NSDictionary* options = [command.arguments objectAtIndex:0 withDefault:nil];

	paymentController.DisableCerfCheck = YES;
	paymentController.enableRotation = YES;
	//paymentController.headerImage = nil;
	[paymentController show];
	NSLog(@"<< start");
}



// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.

- (void)viewDidLoad {
	NSLog(@">> viewDidLoad");
	/* init button to M2ULoginButton. */
	paymentController = [M2UController new];

	[((UIButton*)[self.view viewWithTag:10]) setImage:[paymentController getM2UImageButton:3] forState:UIControlStateNormal];
	paymentController = [paymentController initWithDelegate:self UrlEnv:kTestUrlEnv UrlTest:@"https://www.airasia.com" PayeeCode:@"135" AmountBit:1 Amount:@"900" ReferenceBit:1 ReferenceNo:@"99999999999" AccountBit:1 AccountNo:@"1234567800" URL:@"http://www.gsc.com.my"];

	//add to canvas
	//[self.view addSubview:_M2UButton];

	[super viewDidLoad];
	NSLog(@"<< viewDidLoad");
}



-(void) didComplete:(M2UController*)m2uController{
	/*
	payment is completed
	dialog of payment M2U is dismissed
	you need to code here upon completion of payment
	*/
	NSLog(@"completed");
}

-(void) didIncomplete:(M2UController*)m2uController withError:(BOOL)hasErrorMessage:(NSString*)msg{
	/*
	some incompletes has error but no need
	to display to user.
	it returns hasError = NO, but with Messages.
	Display the message, so then you can handle it.
	*/
	NSLog(@"Incomplete");
	NSLog(@"Error Msg %@",msg);

	//IF an error occur, display to user.
	if (hasErrorMessage) {
		UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"" message:msg delegate:nil cancelButtonTitle:@"Dismiss" otherButtonTitles:nil];

		[alert show];
		//[alert release];
	}
}

@end