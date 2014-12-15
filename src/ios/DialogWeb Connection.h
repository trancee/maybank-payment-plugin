//
//  URL+WEB Connection.h
//  M2U
//
//  Created by Helmi on 2/17/11.
//  Copyright 2011 Terato Tech Sdn Bhd. All rights reserved.
//




#import <Foundation/Foundation.h>
#import "DDSocialDialog.h"




@protocol connectionDelegate <NSObject>
@optional
-(void) connectionFinish:(int)response output:(NSString*)output IsSuccessful:(BOOL)isSuccessful;
@end

typedef enum{
	ksucess,
	kfail,
	kerror,
	
}response;


@interface DialogWeb_Connection : NSObject <UIWebViewDelegate,DDSocialDialogDelegate>{
	UIWebView *web;
	NSMutableData *receivedData;
	id <connectionDelegate> delegate;
	BOOL isCompleted;
	BOOL isSuccessful;
	DDSocialDialog *dialog;
	NSString *errorMessage;
	NSTimer *timer;
	UIActivityIndicatorView *indicator;
	UIView *loadingContainer;
	BOOL isProd;
    BOOL isAuth;
    BOOL DisableCerfCheck;
}
-(id) startWithDelegate:(id <connectionDelegate>)delegates LoginUrl:(NSString*)loginUrl DisableCertCheck:(BOOL)disableCerfCheck;
@property (nonatomic,assign,readwrite) id <connectionDelegate> delegate; 

@end
