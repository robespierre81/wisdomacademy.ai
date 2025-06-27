
const HeadMenu = () => {

    return (
            <div class="c-menu__nav-wrapper" data-menu-nav-wrapper>
        <div class="c-menu__nav-wrapper-inner" data-menu-nav-wrapper-inner>

            <p class="c-menu__tagline">Collaborative Stories for the Collective Imagination</p>

            <nav class="c-menu__nav" data-menu-nav aria-label="Primary">
                <ul id="menu-main-menu" class="c-menu__menu"><li class="hidden-desktop menu-item menu-home"><a href="https://switch-lit.com/">Home</a></li>
                    <li class="menu-item menu-start-a-story"><a href="https://switch-lit.com/start-a-story/">Start a Story</a></li>
                    <li class="menu-item menu-subrosa"><a href="https://switch-lit.com/subrosa/">Subrosa</a></li>
                    <li class="menu-item menu-prompts"><a href="https://switch-lit.com/prompts/">Prompts</a></li>
                    <li class="menu-item menu-library"><a href="https://switch-lit.com/library/">Library</a></li>
                    <li class="active menu-item menu-about"><a href="https://switch-lit.com/about-collaborative-fiction-and-poetry/" aria-current="page">About</a></li>
                    <li class="menu-item menu-faq"><a href="https://switch-lit.com/faq/">FAQ</a></li>
                    <li class="hidden-desktop menu-item menu-contact"><a href="https://switch-lit.com/contact/">Contact</a></li>
                </ul>					</nav>

            <div class="c-menu__footer">
                <p class="c-menu__footer-text"><span>Switch-Lit</span> ©2025</p>

                <nav aria-label="Legal">
                    <ul id="menu-legal" class="c-menu__legal-menu"><li class="menu-item menu-terms-conditions"><a href="https://switch-lit.com/terms-and-conditions/">Terms &#038; Conditions</a></li>
                    </ul>						</nav>
            </div>

            <div class="c-menu__auth-mobile" data-auth-menu-mobile>

            </div>
        </div>
    </div>
        );
};