//
//  M2UController.h
//  M2U
//
//  Created by Helmi on 2/17/11.
//  Copyright 2011 Terato Tech Sdn Bhd. All rights reserved.
//

#import "DialogWeb Connection.h"

@protocol M2UDelegate;
typedef enum{
	kTestUrlEnv,
	kProdUrlEnv
	
}urlEnv;
@interface M2UController : NSObject <connectionDelegate>{
	id <M2UDelegate> delegate;
	BOOL enableRotation;
	UIImage *headerImage;
	DialogWeb_Connection *url;
	NSString *loginField;
    BOOL DisableCerfCheck;

}
//-----

@property (nonatomic,assign,readwrite) id <M2UDelegate> delegate;
@property (nonatomic,retain) UIImage *headerImage;
@property BOOL enableRotation;
@property BOOL DisableCerfCheck;

//----
-(void) show;
-(id) initWithDelegate:(id<M2UDelegate>)delegates UrlEnv:(urlEnv)env UrlTest:(NSString*)urlTest PayeeCode:(NSString*)payeeCode AmountBit:(BOOL)amountBit Amount:(NSString*)amount ReferenceBit:(BOOL)referenceBit ReferenceNo:(NSString *)referenceNo AccountBit:(BOOL)accounBit AccountNo:(NSString *)accountNo URL:(NSString *)Url;
- (UIButton *)getM2UPayButton:(id)target
					   action:(SEL)action
				   buttonType:(int)buttonType
						point:(CGPoint)point;
-(UIImage*) getM2UImageButton:(int) buttonType;

@end


				/*      DELEGATE METHOD      */

@protocol M2UDelegate <NSObject>
@optional
-(void) didComplete:(M2UController*)m2uController IsSuccesful:(BOOL)isSuccesful;
-(void) didIncomplete:(M2UController*)m2uController withError:(BOOL)hasError Message:(NSString*)msg;
@end