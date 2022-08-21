package br.jus.tse.distribuicao_urnas.controller;

import br.jus.tse.distribuicao_urnas.domain.CentroDistribuicao;
import br.jus.tse.distribuicao_urnas.domain.TRE;
import br.jus.tse.distribuicao_urnas.model.ZonaEleitoralDTO;
import br.jus.tse.distribuicao_urnas.repos.CentroDistribuicaoRepository;
import br.jus.tse.distribuicao_urnas.repos.TRERepository;
import br.jus.tse.distribuicao_urnas.service.ZonaEleitoralService;
import br.jus.tse.distribuicao_urnas.util.WebUtils;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/zonaEleitorals")
public class ZonaEleitoralController {

    private final ZonaEleitoralService zonaEleitoralService;
    private final TRERepository tRERepository;
    private final CentroDistribuicaoRepository centroDistribuicaoRepository;

    public ZonaEleitoralController(final ZonaEleitoralService zonaEleitoralService,
            final TRERepository tRERepository,
            final CentroDistribuicaoRepository centroDistribuicaoRepository) {
        this.zonaEleitoralService = zonaEleitoralService;
        this.tRERepository = tRERepository;
        this.centroDistribuicaoRepository = centroDistribuicaoRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("treValues", tRERepository.findAll().stream().collect(
                Collectors.toMap(TRE::getId, TRE::getUf)));
        model.addAttribute("centroDistribuicaoValues", centroDistribuicaoRepository.findAll().stream().collect(
                Collectors.toMap(CentroDistribuicao::getId, CentroDistribuicao::getNome)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("zonaEleitorals", zonaEleitoralService.findAll());
        return "zonaEleitoral/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("zonaEleitoral") final ZonaEleitoralDTO zonaEleitoralDTO) {
        return "zonaEleitoral/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("zonaEleitoral") @Valid final ZonaEleitoralDTO zonaEleitoralDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "zonaEleitoral/add";
        }
        zonaEleitoralService.create(zonaEleitoralDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("zonaEleitoral.create.success"));
        return "redirect:/zonaEleitorals";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("zonaEleitoral", zonaEleitoralService.get(id));
        return "zonaEleitoral/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("zonaEleitoral") @Valid final ZonaEleitoralDTO zonaEleitoralDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "zonaEleitoral/edit";
        }
        zonaEleitoralService.update(id, zonaEleitoralDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("zonaEleitoral.update.success"));
        return "redirect:/zonaEleitorals";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = zonaEleitoralService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            zonaEleitoralService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("zonaEleitoral.delete.success"));
        }
        return "redirect:/zonaEleitorals";
    }

}
