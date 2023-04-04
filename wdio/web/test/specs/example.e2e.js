const AxeBuilder = require('@axe-core/webdriverio').default;

async function axeChecker() {
    const builder = new AxeBuilder({ client: browser });
    const results = await builder.analyze();
    throw new Error(JSON.stringify(results));
}

describe('My Login application', () => {
    it('should login with valid credentials', async () => {
        // await browser.url(`https://the-internet.herokuapp.com/login`);
        await browser.url('https://www.fast.com/') ;
        await browser.pause(5000);
        await axeChecker();

        // var username = $('#username');
        // await username.waitForDisplayed({ timeout: 30000 });
        // await username.click();
        // await username.setValue('tomsmith');
        // await $('#password').setValue('SuperSecretPassword!');
        // await $('button[type="submit"]').click();
        // await expect($('#flash')).toBeExisting();
        // await expect($('#flash')).toHaveTextContaining('You logged into a secure area!');
    })
})