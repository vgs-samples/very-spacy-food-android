# Very Spacy Food <br/> VGS Collect Android SDK Showcase Application 

Very Spacy Food is a food ordering demo application build with [VGSCollectSDK](https://www.verygoodsecurity.com/docs/vgs-collect/android-sdk/overview) for secure collecting credit cards data.

## How to run it?
### Requirements

- Organization with <a href="https://www.verygoodsecurity.com/">VGS</a>

#### Step 1

Install [VGS Collect and pay](https://github.com/verygoodsecurity/collect-and-pay-demo-backend/tree/api) and complete the "How to use" guide.

#### Step 2

Clone Very Spacy Food application repository.

``git@github.com:vgs-samples/very-spacy-food-android.git``

#### Step 3

Go to build.gradle find those lines: 
    
    buildConfigField "String", "VAULT_ID", "<VAULT_ID>"
    buildConfigField "String", "BASE_URL", "<VGS_COLLECT_AND_PAY_SERVER_URL>"
    
and replace `"VAULT_ID"` with your organization <a href="https://www.verygoodsecurity.com/docs/terminology/nomenclature#vault" target="_blank">vault id</a>,
`"BASE_URL"` with your [VGS Collect and pay](https://github.com/verygoodsecurity/collect-and-pay-demo-backend/tree/api) instance url.
 
### Step 4 

Run the application and try to order some Very Spacy Food.</br>

#### When on Add Credit Card Data screen

You can use test credit card data to make the order, e.x.:

``` swift

/// Cardholder Name 
Joe Business

/// Card Number   
4111111111111111

/// Exp. Date  
11/22

/// CVC code
123

```
Press **Save** button. Then data will be submitted to VGS.  
Go to the Logs tab on <a href="http://dashboard.verygoodsecurity.com" target="_blank">Dashboard</a>, find request and secure a payload.  
Instruction for this step you can find <a href="https://www.verygoodsecurity.com/docs/getting-started/quick-integration#securing-inbound-connection" target="_blank">here</a>.

#### When on Checkout screen

Press **Checkout** button. After successful response go to your Stripe dashboard and find your order.

### Useful links

- <a href="https://www.verygoodsecurity.com/docs/vgs-collect/android-sdk/overview" target="_blank">Documentation</a> 
- <a href="https://verygoodsecurity.github.io/vgs-collect-android/" target="_blank">VGSCollectSDK API References</a> 
- <a href="https://github.com/verygoodsecurity/vgs-collect-android" target="_blank">VGSCollectSDK GitHub Repo</a> 