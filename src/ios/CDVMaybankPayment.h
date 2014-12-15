#import <UIKit/UIKit.h>
#import "M2UController.h"

#import <Cordova/CDV.h>
#import <Cordova/CDVPlugin.h>

@interface CDVMaybankPayment :UIViewController<M2UDelegate> {
	M2UController *paymentController;
}

@property(nonatomic,retain) M2UController *paymentController;

@end